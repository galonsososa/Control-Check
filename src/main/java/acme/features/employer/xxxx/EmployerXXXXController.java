
package acme.features.employer.xxxx;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.XXXX.XXXX;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/xxxx/")
public class EmployerXXXXController extends AbstractController<Employer, XXXX> {

	@Autowired
	private EmployerXXXXShowService		showService;

	@Autowired
	private EmployerXXXXCreateService	createService;

	@Autowired
	private EmployerXXXXDeleteService	deleteService;

	@Autowired
	private EmployerXXXXUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
