
package acme.features.sponsor.banner.commercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.CommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCBannerRepository extends AbstractRepository {

	@Query("select cb from CommercialBanner cb where cb.id=?1")
	CommercialBanner findOneById(int id);

	@Query("select s.commercialBanners from Sponsor s where s.id = ?1")
	Collection<CommercialBanner> findManyBySponsorId(int userId);

	//	@Query("select")
	//	Sponsor findSponsorByCBannerId(int cbannerId)

}
