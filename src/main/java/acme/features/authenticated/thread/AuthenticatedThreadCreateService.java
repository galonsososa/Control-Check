
package acme.features.authenticated.thread;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedThreadCreateService implements AbstractCreateService<Authenticated, Thread> {

	@Autowired
	private AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		entity.setCreationMoment(moment);

		request.bind(entity, errors, "authenticated", "moment");
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");
	}

	@Override
	public Thread instantiate(final Request<Thread> request) {
		assert request != null;

		Thread result;
		Authenticated owner;
		Integer userActiveRoleId;

		result = new Thread();
		userActiveRoleId = request.getPrincipal().getActiveRoleId();
		owner = this.repository.findOneAuthenticatedById(userActiveRoleId);
		result.setAuthenticated(owner);

		return result;
	}

	@Override
	public void validate(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Thread> request, final Thread entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
