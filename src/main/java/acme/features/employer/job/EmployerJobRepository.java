
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.XXXX.XXXX;
import acme.entities.YYYY.YYYY;
import acme.entities.applications.Application;
import acme.entities.auditRecords.AuditRecord;
import acme.entities.customparams.Customparams;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("Select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findManyByEmployerId(int employerId);

	@Query("select d from Duty d where d.job.id =?1")
	Collection<Duty> findDutiesByJobId(int id);

	@Query("select a from Application a where a.status= acme.components.Status.PENDING and a.job.id = ?1")
	Collection<Application> findActiveApplicationsByJobId(int id);

	@Query("select e from Employer e where e.userAccount.id = ?1")
	Employer findEmployerByUserAccountId(int id);

	@Query("select sum(d.percentage) from Duty d where d.job.id = ?1")
	Integer sumOfDutiesByJobId(int id);

	@Query("select cp from Customparams cp")
	Customparams findCustomParams();

	@Query("select j from Job j where j.reference = ?1")
	Job existsReference(String reference);

	@Query("Select x from XXXX x where x.job.id=?1")
	XXXX findXXXXByJobId(int id);

	@Query("select a from Application a where a.job.id=?1")
	Collection<Application> findApplicationsByJobId(int id);

	@Query("select ar from AuditRecord ar where ar.job.id = ?1")
	Collection<AuditRecord> findAuditRecordsByJobId(int id);

	@Query("select y from YYYY y where y.application.job.id = ?1")
	YYYY findYYYYByJobId(int id);
}
