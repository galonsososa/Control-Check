
package acme.features.authenticated.message;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.entities.messages.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	private AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		Boolean result;
		Integer threadId;
		Collection<Authenticated> participants;
		Authenticated creator;
		Integer authId;

		authId = request.getPrincipal().getActiveRoleId();
		threadId = request.getModel().getInteger("threadId");
		participants = this.repository.findAuthenticatedsByThreadId(threadId);
		creator = this.repository.findCreatorByThreadId(threadId);
		result = creator.getId() == authId || participants.stream().anyMatch(a -> a.getId() == authId);

		return result;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		Integer a = request.getPrincipal().getActiveRoleId();
		entity.setAuthenticated(this.repository.findOneAuthenticatedByAuthenticatedId(a));
		request.bind(entity, errors, "creationMoment", "authenticated", "thread");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer threadId;
		threadId = request.getModel().getInteger("threadId");
		model.setAttribute("threadId", threadId);
		request.unbind(entity, model, "title", "tags", "body");
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;
		Message result;
		result = new Message();

		int threadId = request.getModel().getInteger("threadId");
		Thread thread = this.repository.findThreadByThreadId(threadId);
		result.setThread(thread);
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);

		Integer a = request.getPrincipal().getActiveRoleId();
		result.setAuthenticated(this.repository.findOneAuthenticatedByAuthenticatedId(a));

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		String confirmation;
		String spamWords;
		Double spamThreshold;
		String body;
		Integer limit;

		if (!errors.hasErrors("confirmation")) {
			confirmation = request.getModel().getString("confirmation");

			errors.state(request, confirmation.equals("true"), "confirmation", "authenticated.message.form.error.not-confirmed");
		}

		if (!errors.hasErrors("body")) {
			spamWords = this.repository.findSpamWords();
			spamThreshold = Double.valueOf(this.repository.findSpamThreshold());
			body = request.getModel().getString("body");

			List<String> spamWordsList;
			List<String> bWords;
			Long coincidences;
			spamWordsList = Arrays.asList(spamWords.split(", "));
			bWords = Arrays.asList(body.replace(".", "").replace(",", "").toLowerCase().split(" "));
			spamWordsList.stream().forEach(x -> x.trim());
			bWords.stream().forEach(x -> x.trim());
			coincidences = bWords.stream().map(x -> spamWordsList.stream().anyMatch(y -> x.contains(y))).filter(x -> x).collect(Collectors.counting());
			limit = Double.valueOf(spamThreshold * bWords.size() / 100).intValue();
			errors.state(request, coincidences <= limit, "body", "authenticated.message.form.error.spam");
			errors.state(request, body.length() < 1024, "body", "authenticated.message.form.error.bodyLength");
		}

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		this.repository.save(entity);
	}

}
