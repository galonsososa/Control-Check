
package acme.features.administrator.auditorRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAuditorRequestRepository extends AbstractRepository {

	@Query("select ar from AuditorRequest ar where ar.id=?1")
	AuditorRequest findOneById(int id);

	@Query("select ar from AuditorRequest ar")
	Collection<AuditorRequest> findManyAll();
}
