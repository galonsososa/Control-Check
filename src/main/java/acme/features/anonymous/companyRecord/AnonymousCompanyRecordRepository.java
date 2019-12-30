
package acme.features.anonymous.companyRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousCompanyRecordRepository extends AbstractRepository {

	@Query("Select c from CompanyRecord c where c.id = ?1")
	CompanyRecord findOneById(int id);

	@Query("Select c from CompanyRecord c")
	Collection<CompanyRecord> findManyAll();

	@Query("Select c from CompanyRecord c where c.stars = 5")
	Collection<CompanyRecord> findTopList();

}
