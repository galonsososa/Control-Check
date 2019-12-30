
package acme.features.authenticated.auditorRequest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.Status;
import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorRequestCreateService implements AbstractCreateService<Authenticated, AuditorRequest> {

	@Autowired
	AuthenticatedAuditorRequestRepository repository;


	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		int accountId = request.getPrincipal().getAccountId();
		UserAccount userAccount = this.repository.findUserAccountById(accountId);

		entity.setUserAccount(userAccount);
		entity.setStatus(Status.PENDING);

	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "statement");

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		int accountId = request.getPrincipal().getAccountId();
		UserAccount userAccount = this.repository.findUserAccountById(accountId);

		entity.setUserAccount(userAccount);
		entity.setStatus(Status.PENDING);

	}

	@Override
	public AuditorRequest instantiate(final Request<AuditorRequest> request) {
		AuditorRequest result;

		result = new AuditorRequest();

		return result;
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//an user can only request to be an auditor once
		int accountId = request.getPrincipal().getAccountId();
		UserAccount userAccount = this.repository.findUserAccountById(accountId);
		AuditorRequest ar = this.repository.findAuditorRequestByUsername(userAccount.getUsername());
		Boolean alredyRequested = ar != null;
		errors.state(request, !alredyRequested, "*", "authenticated.auditor-record.error.alredyRequested");

	}

	@Override
	public void create(final Request<AuditorRequest> request, final AuditorRequest entity) {

		this.repository.save(entity);

	}

}
