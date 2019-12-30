
package acme.features.consumer.offer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.helpers.MessageHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	@Autowired
	ConsumerOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");

	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "minMoney", "maxMoney", "ticker", "moreInfo");
		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		Offer result;
		Date moment;
		result = new Offer();
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);
		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//ticker must be unique
		if (!errors.hasErrors("ticker")) {
			boolean tickerIsNotUnique = !this.uniqueTicker(entity.getTicker());
			String message = MessageHelper.getMessage("consumer.offer.error.ticker");
			if (tickerIsNotUnique) {
				errors.add("ticker", message);
			}
		}

		if (!errors.hasErrors("deadline")) {
			Date now = new Date(System.currentTimeMillis() - 1);
			boolean deadlinePassed = entity.getDeadline().after(now);
			errors.state(request, deadlinePassed, "deadline", "consumer.offer.error.deadline.passed");
		}
		if (!errors.hasErrors("maxMoney")) {
			boolean maxGreaterMin = entity.getMinMoney().getAmount() <= entity.getMaxMoney().getAmount();
			errors.state(request, maxGreaterMin, "maxMoney", "consumer.offer.error.maxMoney.greater");
			boolean moneyCurrencyMax = entity.getMaxMoney().getCurrency().equals("EUR") || entity.getMaxMoney().getCurrency().equals("EUROS") || entity.getMaxMoney().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "maxMoney", "consumer.offer.error.maxMoney.currency");
		}

		if (!errors.hasErrors("minMoney")) {
			boolean moneyCurrencyMin = entity.getMinMoney().getCurrency().equals("EUR") || entity.getMinMoney().getCurrency().equals("EUROS") || entity.getMinMoney().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMin, "minMoney", "consumer.offer.error.minMoney.currency");
		}

		boolean isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "consumer.offer.error.must-accept");
	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {

		this.repository.save(entity);
	}

	public boolean uniqueTicker(final String ticker) {

		Offer o = this.repository.existTicket(ticker);
		Boolean isUnique = o == null;
		return isUnique;
	}
}
