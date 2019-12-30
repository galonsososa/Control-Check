
package acme.features.authenticated.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		return this.repository.findAuthenticatedsById(request.getModel().getInteger("id")).stream().anyMatch(a -> a.getId() == request.getPrincipal().getActiveRoleId())
			|| this.repository.findCreatorById(request.getModel().getInteger("id")).getId() == request.getPrincipal().getActiveRoleId();
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "body", "tags", "thread.title");
	}

	@Override
	public Message findOne(final Request<Message> request) {
		return this.repository.findOneById(request.getModel().getInteger("id"));
	}

}
