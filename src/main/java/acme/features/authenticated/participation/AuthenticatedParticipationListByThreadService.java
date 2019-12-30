
package acme.features.authenticated.participation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Participation;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedParticipationListByThreadService implements AbstractListService<Authenticated, Participation> {

	@Autowired
	private AuthenticatedParticipationRepository repository;


	@Override
	public boolean authorise(final Request<Participation> request) {
		assert request != null;

		Boolean result;
		Integer authId;
		Integer threadId;
		Collection<Authenticated> authenticateds;
		Authenticated creator;

		authId = request.getPrincipal().getActiveRoleId();
		threadId = request.getModel().getInteger("threadId");
		authenticateds = this.repository.findAuthenticatedByThreadId(threadId);
		creator = this.repository.findCreatorByThreadId(threadId);
		result = creator.getId() == authId || authenticateds.stream().anyMatch(a -> a.getId() == authId);

		return result;
	}

	@Override
	public void unbind(final Request<Participation> request, final Participation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "authenticated.userAccount.username", "authenticated.userAccount.identity.name", "authenticated.userAccount.identity.surname");
	}

	@Override
	public Collection<Participation> findMany(final Request<Participation> request) {
		assert request != null;

		Integer threadId;
		Collection<Participation> result;

		threadId = request.getModel().getInteger("threadId");
		result = this.repository.findManyByThreadId(threadId);

		return result;
	}

}
