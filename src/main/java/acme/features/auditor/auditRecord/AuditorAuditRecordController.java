
package acme.features.auditor.auditRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/audit-record/")
public class AuditorAuditRecordController extends AbstractController<Auditor, AuditRecord> {
	// Internal state

	@Autowired
	private AuditorAuditRecordListMineService	listService;

	@Autowired
	private AuditorAuditRecordShowService	showService;

	@Autowired
	private AuditorAuditRecordCreateService	createService;
	
	@Autowired
	private AuditorAuditRecordUpdateService updateService;


	// Constructors

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_BY_JOB , BasicCommand.LIST,  this.listService);		
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
