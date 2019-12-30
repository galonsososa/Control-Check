
package acme.features.sponsor.banner.commercialBanner;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.features.authenticated.sponsor.AuthenticatedSponsorRepository;
import acme.features.sponsor.creditCard.SponsorCreditCardRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCBannerCreateService implements AbstractCreateService<Sponsor, CommercialBanner> {

	@Autowired
	SponsorCBannerRepository		repository;

	@Autowired
	SponsorCreditCardRepository		creditCardRepository;

	@Autowired
	AuthenticatedSponsorRepository	sponsorRepository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		request.getModel().setAttribute("creditCards", this.creditCardRepository.findManyAll());
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetUrl", "creditCard");
		model.setAttribute("creditCards", this.creditCardRepository.findManyAll());

	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		CommercialBanner result;
		result = new CommercialBanner();
		request.getModel().setAttribute("creditCard", this.creditCardRepository.findManyAll());
		CreditCard cc = new CreditCard();
		result.setCreditCard(cc);
		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		for (Iterator<Entry<String, List<String>>> i = errors.iterator(); i.hasNext();) {
			Entry<String, List<String>> it = i.next();
			if (it.getKey().contains("creditCard")) {
				i.remove();
			}
		}
	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {

		int principalId;
		Sponsor sponsor;

		principalId = request.getPrincipal().getAccountId();
		CreditCard cc = this.creditCardRepository.findOneById(entity.getCreditCard().getId());
		sponsor = this.sponsorRepository.findOneSponsorByUserAccountId(principalId);
		Collection<CommercialBanner> commercialBanners = sponsor.getCommercialBanners();
		commercialBanners.add(entity);
		sponsor.setCommercialBanners(commercialBanners);

		entity.setCreditCard(cc);

		this.repository.save(entity);

	}

}
