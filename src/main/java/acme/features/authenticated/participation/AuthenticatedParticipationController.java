
package acme.features.authenticated.participation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.messages.Participation;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/participation/")
public class AuthenticatedParticipationController extends AbstractController<Authenticated, Participation> {

	@Autowired
	private AuthenticatedParticipationListByThreadService	listByThreadService;

	@Autowired
	private AuthenticatedParticipationShowService			showService;

	@Autowired
	private AuthenticatedParticipationAddMemberService		addMemberService;

	@Autowired
	private AuthenticatedParticipationRemoveMemberService	removeMemberService;


	@PostConstruct
	public void initialise() {
		super.addCustomCommand(CustomCommand.LIST_THREAD, BasicCommand.LIST, this.listByThreadService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.ADD_MEMBER, BasicCommand.CREATE, this.addMemberService);
		super.addCustomCommand(CustomCommand.REMOVE_MEMBER, BasicCommand.DELETE, this.removeMemberService);
	}

}
