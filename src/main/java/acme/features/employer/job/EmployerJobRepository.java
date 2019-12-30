
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
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

	@Query("select a from Application a where a.job.id = ?1")
	Collection<Application> findApplicationsByJobId(int id);

	@Query("select e from Employer e where e.userAccount.id = ?1")
	Employer findEmployerByUserAccountId(int id);


	@Query("select sum(d.percentage) from Duty d where d.job.id = ?1")
	Integer sumOfDutiesByJobId(int id);

	@Query("select cp from Customparams cp")
	Customparams findCustomParams();

	@Query("select j from Job j where j.reference = ?1")
	Job existsReference(String reference);


}
