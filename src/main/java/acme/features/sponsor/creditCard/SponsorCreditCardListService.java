
package acme.features.sponsor.creditCard;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class SponsorCreditCardListService implements AbstractListService<Sponsor, CreditCard> {

	@Autowired
	SponsorCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		// TODO Auto-generated method stub que sea el worker que queremos this.repository.getWorkerId == activeRoleId
		//return this.repository.getSponsorId == activeRoleId;
		return true;
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creditCardNumber", "holder");

	}

	@Override
	public Collection<CreditCard> findMany(final Request<CreditCard> request) {
		assert request != null;

		Collection<CreditCard> result;
		result = this.repository.findManyAll();

		return result;
	}

}
