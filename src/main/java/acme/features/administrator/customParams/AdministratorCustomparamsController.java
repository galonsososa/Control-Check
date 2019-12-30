
package acme.features.administrator.customParams;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.customparams.Customparams;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/customparams/")
public class AdministratorCustomparamsController extends AbstractController<Administrator, Customparams> {

	@Autowired
	private AdministratorCustomparamsListService	listService;

	@Autowired
	private AdministratorCustomparamsUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
