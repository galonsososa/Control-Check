
package acme.features.worker.job;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamChecker;
import acme.entities.customparams.Customparams;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.helpers.MessageHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class WorkerJobUpdateService implements AbstractUpdateService<Worker, Job> {

	@Autowired
	WorkerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Worker worker;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		worker = this.repository.findWorkerByUserAccountId(jobId);
		principal = request.getPrincipal();
		result = !job.isFinalMode() && worker.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "finalMode", "description");

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//the sum of the duties must be equal to 100
		int id = entity.getId();
		Integer sumPercentageDuties = this.repository.sumOfDutiesByJobId(id);
		Boolean sums100 = sumPercentageDuties == 100;

		if (entity.isFinalMode() == true) {
			errors.state(request, sums100, "*", "worker.job.error.duties.not100");
		}

		//Deadline must be less than a week from now| Deadline cannot be in the past
		if (!errors.hasErrors("deadline")) {
			Date now = new Date(System.currentTimeMillis() - 1);
			boolean deadlinePassed = entity.getDeadline().after(now);

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.WEEK_OF_YEAR, +1);
			Date weekFromNow = calendar.getTime();
			if (!deadlinePassed) {
				errors.state(request, deadlinePassed, "deadline", "worker.job.error.deadline.passed");
			} else {
				boolean deadlineInAWeek = entity.getDeadline().after(weekFromNow);
				errors.state(request, deadlineInAWeek, "deadline", "worker.job.error.deadline.lessThanAWeek");
			}
		}

		//Spam validations-------------------------------------
		SpamChecker c = new SpamChecker();
		Customparams configuration = this.repository.findCustomParams();

		if (!errors.hasErrors("title")) {
			Boolean isTitleSpam = c.check(configuration, entity.getTitle());
			errors.state(request, !isTitleSpam, "title", "worker.job.error.string.spam");
		}
		if (!errors.hasErrors("reference")) {
			Boolean isReferenceSpam = c.check(configuration, entity.getReference());
			errors.state(request, !isReferenceSpam, "reference", "worker.job.error.string.spam");
		}
		if (!errors.hasErrors("moreInfo")) {
			Boolean isMoreInfoSpam = c.check(configuration, entity.getMoreInfo());
			errors.state(request, !isMoreInfoSpam, "moreInfo", "worker.job.error.string.spam");
		}
		if (!errors.hasErrors("description")) {
			Boolean isDescriptionSpam = c.check(configuration, entity.getDescription());
			errors.state(request, !isDescriptionSpam, "description", "worker.job.error.string.spam");
		}

		//Reference must be unique----------------------------
		if (!errors.hasErrors("reference")) {
			boolean referenceIsNotUnique = !this.uniqueReference(entity.getReference());
			String message = MessageHelper.getMessage("worker.job.error.reference");
			Job jobWithSameReference = this.repository.existsReference(entity.getReference());
			if (referenceIsNotUnique && jobWithSameReference.getId() != entity.getId()) {
				errors.add("reference", message);
			}
		}

		//salary must be in euros
		if (!errors.hasErrors("salary")) {
			boolean moneyCurrencyMax = entity.getSalary().getCurrency().equals("EUR") || entity.getSalary().getCurrency().equals("EUROS") || entity.getSalary().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "salary", "worker.job.error.salary.currency");

		}

	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	public boolean uniqueReference(final String reference) {

		Job j = this.repository.existsReference(reference);
		Boolean isUnique = j == null;
		return isUnique;
	}

}
