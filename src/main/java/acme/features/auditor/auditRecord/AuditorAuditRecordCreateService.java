
package acme.features.auditor.auditRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository repository;



	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "job", "auditor");

	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("jobId", request.getModel().getInteger("jobId"));
		request.unbind(entity, model, "title", "draftMode", "body");

	}

	@Override
	public AuditRecord instantiate(final Request<AuditRecord> request) {
		AuditRecord result=new AuditRecord();
		int jobId = request.getModel().getInteger("jobId");
		Job job = this.repository.findOneByJobId(jobId);
		result.setJob(job);
		
		int auditorId = request.getPrincipal().getActiveRoleId();
		Auditor auditor = this.repository.findOneAuditorByAuditRecordId(auditorId);
		result.setAuditor(auditor);
		
		Date moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);


		return result;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		

	}

	@Override
	public void create(final Request<AuditRecord> request, final AuditRecord entity) {
		
		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
