
package acme.features.worker.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Duty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerDutyRepository extends AbstractRepository {

	@Query("Select d from Duty d where d.id=?1")
	Duty findOneById(int id);

	@Query("select d from Duty d where d.job.id =?1")
	Collection<Duty> findMany(int id);

}
