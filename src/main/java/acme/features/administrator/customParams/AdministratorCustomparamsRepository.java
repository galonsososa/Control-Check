
package acme.features.administrator.customParams;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customparams.Customparams;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCustomparamsRepository extends AbstractRepository {

	@Query("select a from Customparams a")
	Collection<Customparams> findMany();

	@Query("select c from Customparams c")
	Customparams findOne();

}
