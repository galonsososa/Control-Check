
package acme.features.employer.xxxx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.XXXX.XXXX;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerXXXXShowService implements AbstractShowService<Employer, XXXX> {

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

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobByJobId(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		xxxx = this.repository.findOneByJobId(jobId);
		XXXXCreated = xxxx != null;

		result = employer.getUserAccount().getId() == principal.getAccountId() && XXXXCreated;
		//can't access if is not the employer that created the job or if there is not an xxxx created for that job

		return result;
	}

	@Override
	public void unbind(final Request<XXXX> request, final XXXX entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description", "moreInfo");
		model.setAttribute("isJobFinalMode", entity.getJob().isFinalMode());
	}

	@Override
	public XXXX findOne(final Request<XXXX> request) {
		assert request != null;

		XXXX result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneByJobId(id);

		return result;
	}

}
