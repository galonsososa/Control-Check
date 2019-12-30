
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamChecker;
import acme.entities.customparams.Customparams;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "percentage");
		int jobId = request.getModel().getInteger("jobId");
		model.setAttribute("jobId", jobId);

	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		Duty result;

		int jobId;
		Job job;
		result = new Duty();

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobById(jobId);

		result.setJob(job);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Spam validations
		SpamChecker c = new SpamChecker();
		Customparams configuration = this.repository.findCustomParams();

		if (!errors.hasErrors("title")) {
			Boolean isTitleSpam = c.check(configuration, entity.getTitle());
			errors.state(request, !isTitleSpam, "title", "employer.duty.error.string.spam");
		}

		if (!errors.hasErrors("description")) {
			Boolean isDescriptionSpam = c.check(configuration, entity.getDescription());
			errors.state(request, !isDescriptionSpam, "description", "employer.duty.error.string.spam");
		}
		//minimun time percetage 1% and maximun 100%
		if (!errors.hasErrors("percentage")) {
			boolean percentageOutbound = entity.getPercentage() < 1 || entity.getPercentage() > 100;
			errors.state(request, !percentageOutbound, "percentage", "employer.duty.error.percentage.outbound");
		}
	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		//	request.setMethod(HttpMethod.GET);
		this.repository.save(entity);

	}

}
