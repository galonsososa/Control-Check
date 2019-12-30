
package acme.features.sponsor.creditCard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/credit-card/")
public class SponsorCreditCardController extends AbstractController<Sponsor, CreditCard> {

	@Autowired
	private SponsorCreditCardListService	listService;

	@Autowired
	private SponsorCreditCardShowService	showService;

	@Autowired
	private SponsorCreditCardCreateService	createService;

	@Autowired
	private SponsorCreditCardDeleteService	deleteService;

	@Autowired
	private SponsorCreditCardUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		//		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);

	}

}
