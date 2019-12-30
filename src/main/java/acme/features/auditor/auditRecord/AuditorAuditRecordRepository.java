
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("Select a from AuditRecord a where a.id = ?1")
	AuditRecord findOneById(int id);

	@Query("select a from AuditRecord a")
	Collection<AuditRecord> findManyAll();

	@Query("select a from AuditRecord a where a.auditor.id = ?1")
	Collection<AuditRecord> findManyByAuditorId(int auditorId);

	@Query("select a from AuditRecord a where a.draftMode = true and a.job.id = ?1")
	Collection<AuditRecord> findManyByJobId(int jobId);

	@Query("select j from Job j where j.id=?1")
	Job findOneByJobId(int id);

	@Query("select a.auditor from AuditRecord a where a.auditor.id=?1")
	Auditor findOneAuditorByAuditRecordId(int id);

}
