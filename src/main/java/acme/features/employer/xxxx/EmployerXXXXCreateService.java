
package acme.features.employer.xxxx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamChecker;
import acme.entities.XXXX.XXXX;
import acme.entities.customparams.Customparams;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerXXXXCreateService implements AbstractCreateService<Employer, XXXX> {

	@Autowired
	EmployerXXXXRepository repository;


	@Override
	public boolean authorise(final Request<XXXX> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;
		XXXX xxxx;
		Boolean XXXXCreated;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobByJobId(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		xxxx = this.repository.findOneByJobId(jobId);
		XXXXCreated = xxxx != null;

		result = employer.getUserAccount().getId() == principal.getAccountId() && !XXXXCreated;
		//can't access if is not the employer that created the job or if there is an XXXX alredy created for that job

		return result;
	}

	@Override
	public void bind(final Request<XXXX> request, final XXXX entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<XXXX> request, final XXXX entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description", "moreInfo");
		int jobId = request.getModel().getInteger("jobId");
		model.setAttribute("jobId", jobId);

	}

	@Override
	public XXXX instantiate(final Request<XXXX> request) {
		XXXX result;

		int jobId;
		Job job;
		result = new XXXX();

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobByJobId(jobId);

		result.setJob(job);

		return result;
	}

	@Override
	public void validate(final Request<XXXX> request, final XXXX entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Spam validations
		SpamChecker c = new SpamChecker();
		Customparams configuration = this.repository.findCustomParams();

		if (!errors.hasErrors("description")) {
			Boolean isDescriptionSpam = c.check(configuration, entity.getDescription());
			errors.state(request, !isDescriptionSpam, "description", "employer.XXXX.error.string.spam");
		}
		if (!errors.hasErrors("moreInfo")) {
			Boolean isMoreInfoSpam = c.check(configuration, entity.getMoreInfo());
			errors.state(request, !isMoreInfoSpam, "moreInfo", "employer.XXXX.error.string.spam");
		}

	}

	@Override
	public void create(final Request<XXXX> request, final XXXX entity) {

		this.repository.save(entity);

	}

}
