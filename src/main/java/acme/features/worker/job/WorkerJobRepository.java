
package acme.features.worker.job;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.customparams.Customparams;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerJobRepository extends AbstractRepository {

	@Query("Select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select d from Duty d where d.job.id =?1")
	Collection<Duty> findDutiesByJobId(int id);

	@Query("select a from Application a where a.job.id = ?1")
	Collection<Application> findApplicationsByJobId(int id);

	@Query("select e from Worker e where e.userAccount.id = ?1")
	Worker findWorkerByUserAccountId(int id);

	@Query("select sum(d.percentage) from Duty d where d.job.id = ?1")
	Integer sumOfDutiesByJobId(int id);

	@Query("select cp from Customparams cp")
	Customparams findCustomParams();

	@Query("select j from Job j where j.reference = ?1")
	Job existsReference(String reference);

	@Query("select j from Job j where j.id not in (select a.job from Application a where a.status='ACCEPTED') and j.finalMode=true and j.deadline > ?1")
	Collection<Job> findJobsWithoutWorkerInFinalModeDeadlineNotPast(Date d);

}
