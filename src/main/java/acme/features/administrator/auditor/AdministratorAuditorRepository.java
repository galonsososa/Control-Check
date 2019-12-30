
package acme.features.administrator.auditor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditorRequests.AuditorRequest;
import acme.entities.roles.Auditor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAuditorRepository extends AbstractRepository {

	@Query("select a from Auditor a where a.userAccount.id = ?1")
	Auditor findOneAuditorByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select ar from AuditorRequest ar where ar.id=?1")
	AuditorRequest findOneAuditorRequestById(int id);

	@Query("select ua from UserAccount ua where ua.username = ?1")
	UserAccount findOneUserAccountByUsername(String username);

}
