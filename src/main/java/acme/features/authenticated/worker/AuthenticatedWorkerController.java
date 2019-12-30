
package acme.features.authenticated.worker;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Worker;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/worker/")
public class AuthenticatedWorkerController extends AbstractController<Authenticated, Worker> {
	// Internal state

	@Autowired
	private AuthenticatedWorkerCreateService	createService;

	@Autowired
	private AuthenticatedWorkerUpdateService	updateService;


	// Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
