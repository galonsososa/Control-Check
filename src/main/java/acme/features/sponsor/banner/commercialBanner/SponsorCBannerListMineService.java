
package acme.features.sponsor.banner.commercialBanner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class SponsorCBannerListMineService implements AbstractListService<Sponsor, CommercialBanner> {

	@Autowired
	SponsorCBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		// TODO Auto-generated method stub que sea el worker que queremos this.repository.getWorkerId == activeRoleId
		//return this.repository.getSponsorId == activeRoleId;
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetUrl");

	}

	@Override
	public Collection<CommercialBanner> findMany(final Request<CommercialBanner> request) {
		assert request != null;

		Collection<CommercialBanner> result;
		Principal principal;
		int principalId;

		principal = request.getPrincipal();
		principalId = principal.getActiveRoleId();

		result = this.repository.findManyBySponsorId(principalId);

		return result;
	}

}
