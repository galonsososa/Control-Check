
package acme.features.administrator.average;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAverageRepository extends AbstractRepository {

	@Query("Select a from Employer a where a.id = ?1")
	Employer findOneById(int id);

	@Query("select a from Employer a")
	Collection<Employer> findManyAll();
}
