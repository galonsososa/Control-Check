
package acme.features.sponsor.banner.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNCBannerRepository extends AbstractRepository {

	@Query("select ncb from NonCommercialBanner ncb where ncb.id=?1")
	NonCommercialBanner findOneById(int id);

	@Query("select s.nonCommercialBanners from Sponsor s where s.id = ?1")
	Collection<NonCommercialBanner> findManyBySponsorId(int userId);

	//	@Query("select")
	//	Sponsor findSponsorByCBannerId(int cbannerId)

}
