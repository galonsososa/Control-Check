
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customparams.Customparams;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("Select d from Duty d where d.id=?1")
	Duty findOneById(int id);

	@Query("select d from Duty d where d.job.id =?1")
	Collection<Duty> findMany(int id);

	@Query("select j from Job j where j.id=?1")
	Job findOneJobById(int jobId);

	@Query("select cp from Customparams cp")
	Customparams findCustomParams();
}
