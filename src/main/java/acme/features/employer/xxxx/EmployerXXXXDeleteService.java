
package acme.features.employer.xxxx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.XXXX.XXXX;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerXXXXDeleteService implements AbstractDeleteService<Employer, XXXX> {

	@Autowired
	EmployerXXXXRepository repository;


	@Override
	public boolean authorise(final Request<XXXX> request) {
		assert request != null;

		boolean result;
		int id;
		Job job;
		Employer employer;
		Principal principal;

		id = request.getModel().getInteger("id");
		job = this.repository.findJobByXXXXId(id);
		employer = job.getEmployer();
		principal = request.getPrincipal();

		result = employer.getUserAccount().getId() == principal.getAccountId();
		//can't delete if is not the same employer that created the job
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

	}

	@Override
	public XXXX findOne(final Request<XXXX> request) {
		assert request != null;

		XXXX result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOne(id);

		return result;
	}

	@Override
	public void validate(final Request<XXXX> request, final XXXX entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<XXXX> request, final XXXX entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
