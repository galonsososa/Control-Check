
package acme.features.sponsor.creditCard;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.features.authenticated.sponsor.AuthenticatedSponsorRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCreditCardCreateService implements AbstractCreateService<Sponsor, CreditCard> {

	@Autowired
	SponsorCreditCardRepository		repository;

	@Autowired
	AuthenticatedSponsorRepository	sponsorRepository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creditCardNumber", "holder", "brand", "cvv", "expirationDate");

	}

	@Override
	public CreditCard instantiate(final Request<CreditCard> request) {
		CreditCard result;
		result = new CreditCard();
		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("expirationDate")) {
			Date now = new Date(System.currentTimeMillis() - 1);
			boolean deadlinePassed = entity.getExpirationDate().after(now);
			errors.state(request, deadlinePassed, "expirationDate", "sponsor.banner.error.expiration.passed");
		}
	}

	@Override
	public void create(final Request<CreditCard> request, final CreditCard entity) {

		this.repository.save(entity);

	}

}
