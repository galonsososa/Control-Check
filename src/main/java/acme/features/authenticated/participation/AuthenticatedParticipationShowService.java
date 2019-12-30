
package acme.features.authenticated.participation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Participation;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedParticipationShowService implements AbstractShowService<Authenticated, Participation> {

	@Autowired
	private AuthenticatedParticipationRepository repository;


	@Override
	public boolean authorise(final Request<Participation> request) {
		assert request != null;

		Boolean result;
		Collection<Authenticated> authenticateds;
		Integer id;
		Integer threadId;
		Integer authId;
		Authenticated creator;

		id = request.getModel().getInteger("id");
		threadId = this.repository.findThreadIdById(id);
		authId = request.getPrincipal().getActiveRoleId();
		authenticateds = this.repository.findAuthenticatedByThreadId(threadId);
		creator = this.repository.findCreatorById(id);
		result = creator.getId() == authId || authenticateds.stream().anyMatch(a -> a.getId() == authId);

		return result;
	}

	@Override
	public void unbind(final Request<Participation> request, final Participation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		Integer id;
		Integer authId;
		Authenticated creator;
		Boolean isOwner;

		id = request.getModel().getInteger("id");
		authId = request.getPrincipal().getActiveRoleId();
		creator = this.repository.findCreatorById(id);
		isOwner = authId == creator.getId();
		
		model.setAttribute("isOwner", isOwner);
		
		request.unbind(entity, model, "authenticated.userAccount.username", "thread.title", "authenticated.userAccount.identity.name", "authenticated.userAccount.identity.surname", "authenticated.userAccount.identity.email");
	}

	@Override
	public Participation findOne(final Request<Participation> request) {
		assert request != null;

		Integer id;
		Participation result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
