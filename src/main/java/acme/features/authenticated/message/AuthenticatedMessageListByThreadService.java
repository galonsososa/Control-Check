
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListByThreadService implements AbstractListService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageRepository repository;


	// AbstractListService<Authenticated, Message> interface ----------------

	@Override
	public boolean authorise(final Request<Message> request) {
		return this.repository.findAuthenticatedsByThreadId(request.getModel().getInteger("threadId")).stream().anyMatch(a -> a.getId() == request.getPrincipal().getActiveRoleId())
			|| this.repository.findCreatorByThreadId(request.getModel().getInteger("threadId")).getId() == request.getPrincipal().getActiveRoleId();
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment");
	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		return this.repository.findManyByThreadId(request.getModel().getInteger("threadId"));
	}

}
