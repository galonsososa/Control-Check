
package acme.features.authenticated.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedThreadShowService implements AbstractShowService<Authenticated, Thread> {

	@Autowired
	AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		return this.repository.findAuthenticatedsById(request.getModel().getInteger("id")).stream().map(a -> a.getId()).anyMatch(i -> i == request.getPrincipal().getActiveRoleId());
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("isMine", this.repository.findOwnerById(request.getModel().getInteger("id")).getId() == request.getPrincipal().getActiveRoleId());

		model.setAttribute("threadId", request.getModel().getInteger("id"));

		request.unbind(entity, model, "title", "creationMoment");
	}

	@Override
	public Thread findOne(final Request<Thread> request) {
		return this.repository.findOneById(request.getModel().getInteger("id"));
	}

}
