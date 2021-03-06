
package acme.features.sponsor.banner.commercialBanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/commercial-banner/")
public class SponsorCBannerController extends AbstractController<Sponsor, CommercialBanner> {

	@Autowired
	private SponsorCBannerListMineService	listMineService;

	@Autowired
	private SponsorCBannerShowService		showService;

	@Autowired
	private SponsorCBannerCreateService		createService;

	@Autowired
	private SponsorCBannerUpdateService		updateService;

	@Autowired
	private SponsorCBannerDeleteService		deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
