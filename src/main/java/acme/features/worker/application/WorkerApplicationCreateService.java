
package acme.features.worker.application;

import java.util.Collection;
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

		request.unbind(entity, model, "reference", "statement", "answer", "optionalAnswer", "password");
		int jobId;
		Job job;
		Worker worker;
		Date moment;
		boolean jobHasChallenge;

		moment = new Date(System.currentTimeMillis() - 1);

		int accountId = request.getPrincipal().getAccountId();
		jobId = request.getModel().getInteger("jobId");
		model.setAttribute("jobId", jobId);
		job = this.repository.findOneJobById(jobId);
		jobHasChallenge = job.getChallengeDescription() != null;
		model.setAttribute("jobHasChallenge", jobHasChallenge);

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

		//a worker can't apply more than once to the same job
		int accountId = request.getPrincipal().getActiveRoleId();
		int jobId = request.getModel().getInteger("jobId");
		Collection<Worker> workers = this.repository.findWorkersThatAppliedJobWithId(jobId);
		Worker worker = this.repository.findOneWorkerById(accountId);
		Boolean alredyApplied = workers.contains(worker);
		errors.state(request, !alredyApplied, "*", "worker.application.error.alredyApplied");

		Job job = this.repository.findOneJobById(jobId);
		Boolean jobHasChallenge = job.getChallengeDescription() != null;

		if (jobHasChallenge) {
			//if answer is null, then optional answer and password must also be null
			if (entity.getAnswer().isEmpty() && (!entity.getOptionalAnswer().isEmpty() || !entity.getPassword().isEmpty())) {
				errors.add("answer", "worker.application.error.answer.notNull");
			}
			//if optionalAnswer is null, then the password must also be null
			if (entity.getOptionalAnswer().isEmpty() && !entity.getPassword().isEmpty()) {
				errors.add("optionalAnswer", "worker.application.error.optionalAnswer.notNull");
			}
			//password validation
			if (!entity.getPassword().isEmpty()) {
				PasswordChecker p = new PasswordChecker();
				errors.state(request, p.PasswordCheck(entity.getPassword(), 6, 3, 3, 3), "password", "worker.application.error.password.invalid");
			}
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
