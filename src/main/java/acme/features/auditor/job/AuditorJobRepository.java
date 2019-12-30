 
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	@Query("Select j from Job j where j.id = ?1")
	Job findOneById(int id);

	@Query("select j from Job j")
	Collection<Job> findManyAll();
	
	@Query("select j from Job j where j not in (select au.job from AuditRecord au where au.auditor.id = ?1 and au.job.id = job.id)")
	Collection<Job> findManyNotMineByAuditorId(int auditorId);

	@Query("select j from Job j where j in (select au.job from AuditRecord au where au.auditor.id = ?1 and au.job.id = job.id)")
	Collection<Job> findManyMineByAuditorId(int auditorId);
	
}
