
package acme.features.employer.job;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamChecker;
import acme.entities.customparams.Customparams;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.helpers.MessageHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
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

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "description");

	}

	@Override
	public Job instantiate(final Request<Job> request) {
		Job result;

		int id = request.getPrincipal().getAccountId();
		Employer employer = this.repository.findEmployerByUserAccountId(id);

		result = new Job();

		result.setEmployer(employer);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Deadline must be less than a week from now| Deadline cannot be in the past
		if (!errors.hasErrors("deadline")) {
			Date now = new Date(System.currentTimeMillis() - 1);
			boolean deadlinePassed = entity.getDeadline().after(now);

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.WEEK_OF_YEAR, +1);
			Date weekFromNow = calendar.getTime();
			if (!deadlinePassed) {
				errors.state(request, deadlinePassed, "deadline", "employer.job.error.deadline.passed");
			} else {
				boolean deadlineInAWeek = entity.getDeadline().after(weekFromNow);
				errors.state(request, deadlineInAWeek, "deadline", "employer.job.error.deadline.lessThanAWeek");
			}
		}

		//Spam validations-------------------------------------
		SpamChecker c = new SpamChecker();
		Customparams configuration = this.repository.findCustomParams();

		if (!errors.hasErrors("title")) {
			Boolean isTitleSpam = c.check(configuration, entity.getTitle());
			errors.state(request, !isTitleSpam, "title", "employer.job.error.string.spam");
		}
		if (!errors.hasErrors("reference")) {
			Boolean isReferenceSpam = c.check(configuration, entity.getReference());
			errors.state(request, !isReferenceSpam, "reference", "employer.job.error.string.spam");
		}
		if (!errors.hasErrors("moreInfo")) {
			Boolean isMoreInfoSpam = c.check(configuration, entity.getMoreInfo());
			errors.state(request, !isMoreInfoSpam, "moreInfo", "employer.job.error.string.spam");
		}
		if (!errors.hasErrors("description")) {
			Boolean isDescriptionSpam = c.check(configuration, entity.getDescription());
			errors.state(request, !isDescriptionSpam, "description", "employer.job.error.string.spam");
		}

		//Reference must be unique----------------------------
		if (!errors.hasErrors("reference")) {
			boolean referenceIsNotUnique = !this.uniqueReference(entity.getReference());
			String message = MessageHelper.getMessage("employer.job.error.reference");
			if (referenceIsNotUnique) {
				errors.add("reference", message);
			}
		}

		//salary must be in euros
		if (!errors.hasErrors("salary")) {
			boolean moneyCurrencyMax = entity.getSalary().getCurrency().equals("EUR") || entity.getSalary().getCurrency().equals("EUROS") || entity.getSalary().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "salary", "employer.job.error.salary.currency");

		}

	}

	@Override
	public void create(final Request<Job> request, final Job entity) {

		this.repository.save(entity);

	}

	public boolean uniqueReference(final String reference) {

		Job j = this.repository.existsReference(reference);
		Boolean isUnique = j == null;
		return isUnique;
	}

}
