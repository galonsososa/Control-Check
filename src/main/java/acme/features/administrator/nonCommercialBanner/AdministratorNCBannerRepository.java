
package acme.features.administrator.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNCBannerRepository extends AbstractRepository {

	@Query("select n from NonCommercialBanner n where n.id = ?1")
	NonCommercialBanner findOneById(int id);

	@Query("select n from NonCommercialBanner n")
	Collection<NonCommercialBanner> findManyAll();

}
