
package acme.features.employer.xxxx;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.XXXX.XXXX;
import acme.entities.customparams.Customparams;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerXXXXRepository extends AbstractRepository {

	@Query("select x from XXXX x where x.id = ?1")
	XXXX findOne(int id);

	@Query("select x from XXXX x where x.job.id=?1")
	XXXX findOneByJobId(int jobId);

	@Query("select j from Job j where j.id=?1")
	Job findOneJobByJobId(int jobId);

	@Query("select x.job from XXXX x where x.id = ?1")
	Job findJobByXXXXId(int id);

	@Query("select cp from Customparams cp")
	Customparams findCustomParams();

}
