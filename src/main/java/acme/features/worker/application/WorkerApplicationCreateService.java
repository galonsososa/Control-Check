
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.PasswordChecker;
import acme.components.Status;
import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.helpers.MessageHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	private WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "statement", "answer", "bow", "password");
		int jobId;
		Job job;
		Worker worker;
		Date moment;
		boolean jobHasPust;

		moment = new Date(System.currentTimeMillis() - 1);

		int accountId = request.getPrincipal().getAccountId();
		jobId = request.getModel().getInteger("jobId");
		model.setAttribute("jobId", jobId);
		job = this.repository.findOneJobById(jobId);
		//------------------------------------------------jobHasPust
		jobHasPust = true;
		if (job.getPust() != null) {
			jobHasPust = !job.getPust().isEmpty();
		}
		model.setAttribute("jobHasPust", jobHasPust);

		//----------------------------------------------------

		worker = this.repository.findOneWorkerById(accountId);

		entity.setMoment(moment);
		entity.setJob(job);
		entity.setStatus(Status.PENDING);
		entity.setWorker(worker);

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result;

		result = new Application();

		//------
		int jobId;
		Job job;
		Worker worker;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		int accountId = request.getPrincipal().getActiveRoleId();
		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobById(jobId);
		worker = this.repository.findOneWorkerById(accountId);

		result.setMoment(moment);
		result.setJob(job);
		result.setStatus(Status.PENDING);
		result.setWorker(worker);
		//-----

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Reference must be unique----------------------------
		if (!errors.hasErrors("reference")) {
			boolean referenceIsNotUnique = !this.uniqueReference(entity.getReference());
			String message = MessageHelper.getMessage("worker.application.error.reference");
			if (referenceIsNotUnique) {
				errors.add("reference", message);
			}
		}

		int jobId = request.getModel().getInteger("jobId");
		Job job = this.repository.findOneJobById(jobId);
		//------------------------------------------------jobHasPust
		Boolean jobHasPust = true;
		if (job.getPust() != null) {
			jobHasPust = !job.getPust().isEmpty();
		}
		//------------------------------------------------

		if (jobHasPust) {
			//if answer is null, then bow and password must also be null
			if (entity.getAnswer().isEmpty() && (!entity.getBow().isEmpty() || !entity.getPassword().isEmpty())) {
				errors.add("answer", MessageHelper.getMessage("worker.application.error.answer.notNull"));
			}
			//if bow is null, then the password must also be null
			if (entity.getBow().isEmpty() && !entity.getPassword().isEmpty()) {
				errors.add("bow", MessageHelper.getMessage("worker.application.error.bow.notNull"));
			}
			//password validation
			if (!entity.getPassword().isEmpty()) {
				PasswordChecker p = new PasswordChecker();
				errors.state(request, p.PasswordCheck(entity.getPassword(), 10, 1, 1, 1), "password", "worker.application.error.password.invalid");
			}
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	public boolean uniqueReference(final String reference) {

		Application j = this.repository.existsReference(reference);
		Boolean isUnique = j == null;
		return isUnique;
	}

}
