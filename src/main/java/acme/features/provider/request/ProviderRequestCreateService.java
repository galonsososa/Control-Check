
package acme.features.provider.request;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Request;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.helpers.MessageHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderRequestCreateService implements AbstractCreateService<Provider, Request> {

	@Autowired
	ProviderRequestRepository repository;


	@Override
	public boolean authorise(final acme.framework.components.Request<Request> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final acme.framework.components.Request<Request> request, final Request entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "deadline", "ticker", "description", "reward");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Request instantiate(final acme.framework.components.Request<Request> request) {
		Request result;
		Date moment;
		result = new Request();
		moment = new Date(System.currentTimeMillis() - 1);

		result.setMoment(moment);
		return result;
	}

	@Override
	public void validate(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("ticker")) {
			boolean tickerIsNotUnique = !this.tickerExist(entity.getTicker());
			String message = MessageHelper.getMessage("consumer.request.error.ticker");
			if (tickerIsNotUnique) {
				errors.add("ticker", message);
			}
		}
		if (!errors.hasErrors("deadline")) {
			Date now = new Date(System.currentTimeMillis() - 1);
			boolean deadlinePassed = entity.getDeadline().after(now);
			errors.state(request, deadlinePassed, "deadline", "provider.request.error.deadline.passed");
		}

		if (!errors.hasErrors("reward")) {
			boolean rewardInEur = entity.getReward().getCurrency().equals("EUR") || entity.getReward().getCurrency().equals("EUROS") || entity.getReward().getCurrency().equals("€");
			errors.state(request, rewardInEur, "reward", "provider.request.error.reward.currency");
		}
		boolean isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "provider.request.error.must-accept");

	}

	@Override
	public void create(final acme.framework.components.Request<Request> request, final Request entity) {
		this.repository.save(entity);
	}

	public boolean tickerExist(final String ticker) {

		Request r = this.repository.existTicker(ticker);
		Boolean isUnique = r == null;
		return isUnique;
	}
}
