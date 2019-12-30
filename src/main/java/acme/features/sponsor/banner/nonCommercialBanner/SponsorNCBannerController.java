
package acme.features.sponsor.banner.nonCommercialBanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.banners.NonCommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/non-commercial-banner/")
public class SponsorNCBannerController extends AbstractController<Sponsor, NonCommercialBanner> {

	@Autowired
	private SponsorNCBannerListMineService	listMineService;

	@Autowired
	private SponsorNCBannerShowService		showService;

	@Autowired
	private SponsorNCBannerCreateService	createService;

	@Autowired
	private SponsorNCBannerUpdateService	updateService;

	@Autowired
	private SponsorNCBannerDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
