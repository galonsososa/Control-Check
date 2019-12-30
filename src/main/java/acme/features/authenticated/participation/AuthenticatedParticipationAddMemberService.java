
package acme.features.authenticated.participation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Participation;
import acme.entities.messages.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedParticipationAddMemberService implements AbstractCreateService<Authenticated, Participation> {

	@Autowired
	private AuthenticatedParticipationRepository repository;


	@Override
	public boolean authorise(final Request<Participation> request) {
		assert request != null;

		Boolean result;
		Integer threadId;
		Integer authId;
		Authenticated creator;

		threadId = request.getModel().getInteger("threadId");
		authId = request.getPrincipal().getActiveRoleId();
		creator = this.repository.findCreatorByThreadId(threadId);
		result = creator.getId() == authId;

		return result;
	}

	@Override
	public void bind(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "authenticated.userAccount.username");

	}

	@Override
	public void unbind(final Request<Participation> request, final Participation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("threadId", request.getModel().getInteger("threadId"));

		request.unbind(entity, model);
	}

	@Override
	public Participation instantiate(final Request<Participation> request) {
		assert request != null;
		Participation result;

		result = new Participation();

		return result;
	}

	@Override
	public void validate(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		UserAccount exists;
		Participation duplicated;
		Authenticated creator;
		Authenticated user;
		String username;
		Integer threadId;

		if (!errors.hasErrors("authenticated.userAccount.username")) {
			username = request.getModel().getString("authenticated.userAccount.username");
			exists = this.repository.findUserAccountByUserAccoundUsername(username);

			errors.state(request, exists != null, "authenticated.userAccount.username", "authenticated.participant.form.error.user-not-found");

			if (exists == null) {
				return;
			}

			threadId = request.getModel().getInteger("threadId");
			duplicated = this.repository.findOneByParticipantUsernameAndThreadId(username, threadId);

			errors.state(request, duplicated == null, "authenticated.userAccount.username", "authenticated.participant.form.error.duplicated-user");

			user = this.repository.findAutehnticatedByUserAccountUsername(username);
			creator = this.repository.findCreatorByThreadId(threadId);

			errors.state(request, creator.getId() != user.getId(), "authenticated.userAccount.username", "authenticated.participant.form.error.owner");
		}

	}

	@Override
	public void create(final Request<Participation> request, final Participation entity) {
		assert entity != null;

		Participation result;
		Authenticated authenticated;
		Thread thread;
		Integer threadId;
		String username;

		result = entity;
		threadId = request.getModel().getInteger("threadId");
		thread = this.repository.findThreadByThreadId(threadId);
		username = request.getModel().getString("authenticated.userAccount.username");
		authenticated = this.repository.findAutehnticatedByUserAccountUsername(username);

		result.setAuthenticated(authenticated);
		result.setThread(thread);

		this.repository.save(result);
	}

}
