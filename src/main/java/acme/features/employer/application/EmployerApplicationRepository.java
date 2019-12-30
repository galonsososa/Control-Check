
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id=?1")
	Application findOneById(int id);

	@Query("select a from Application a where a.job.employer.id = ?1 group by a.job.reference, a.job.finalMode, a.moment")
	Collection<Application> findManyByEmployerId(int employerId);

	@Query("select e from Employer e where e.id in(select a.job.employer.id from Application a where a.id=?1)")
	Employer findEmployerByApplicationId(int id);
}
