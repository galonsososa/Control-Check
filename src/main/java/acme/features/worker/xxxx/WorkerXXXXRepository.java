
package acme.features.worker.xxxx;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.XXXX.XXXX;
import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerXXXXRepository extends AbstractRepository {

	@Query("select x from XXXX x where x.job.id = ?1")
	XXXX findOneByJobId(int jobId);

	@Query("select a from Application a where a.job.id = ?1")
	Application findApplicationByJobId(int jobId);

}
