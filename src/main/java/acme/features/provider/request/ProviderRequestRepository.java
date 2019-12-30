
package acme.features.provider.request;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.Request;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ProviderRequestRepository extends AbstractRepository {

	@Query("Select r from Request r where r.id = ?1")
	Request findOneById(int id);

	@Query("select r from Request r")
	Collection<Request> findManyAll();

	@Query("select c from Request c where c.deadline > ?1")
	Collection<Request> findManyAllFuture(Date d);

	@Query("select a from Request a where a.ticker = ?1")
	Request existTicker(String ticker);

}
