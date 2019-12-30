
package acme.features.authenticated.employer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/employer/")
public class AuthenticatedEmployerController extends AbstractController<Authenticated, Employer> {
	// Internal state

	@Autowired
	private AuthenticatedEmployerCreateService	createService;

	@Autowired
	private AuthenticatedEmployerUpdateService	updateService;


	// Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
