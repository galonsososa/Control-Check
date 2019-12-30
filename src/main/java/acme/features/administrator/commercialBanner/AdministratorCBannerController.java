
package acme.features.administrator.commercialBanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.banners.CommercialBanner;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/commercial-banner/")
public class AdministratorCBannerController extends AbstractController<Administrator, CommercialBanner> {

	@Autowired
	private AdministratorCBannerListService		listService;

	@Autowired
	private AdministratorCBannerShowService		showService;

	@Autowired
	private AdministratorCBannerCreateService	createService;

	@Autowired
	private AdministratorCBannerUpdateService	updateService;

	@Autowired
	private AdministratorCBannerDeleteService	deleteService;

	//credit card


	// Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

		//credit card
	}

}
