
package acme.features.sponsor.banner.commercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.features.sponsor.creditCard.SponsorCreditCardRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorCBannerShowService implements AbstractShowService<Sponsor, CommercialBanner> {

	@Autowired
	SponsorCBannerRepository	repository;

	@Autowired
	SponsorCreditCardRepository	creditCardRepository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;
		boolean result = true;
		return result;
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetUrl", "creditCard.creditCardNumber");

	}

	@Override
	public CommercialBanner findOne(final Request<CommercialBanner> request) {
		assert request != null;

		CommercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
