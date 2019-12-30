
package acme.features.authenticated.auditorRequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditorRequestRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id=?1")
	UserAccount findUserAccountById(int id);

	@Query("select ua from AuditorRequest ua where ua.userAccount.username = ?1")
	AuditorRequest findAuditorRequestByUsername(String username);

}
