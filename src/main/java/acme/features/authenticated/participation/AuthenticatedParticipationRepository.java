
package acme.features.authenticated.participation;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.Participation;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedParticipationRepository extends AbstractRepository {

	@Query("select p from Participation p where p.authenticated.userAccount.username = ?1 and p.thread.id = ?2")
	Participation findOneByParticipantUsernameAndThreadId(String username, Integer threadId);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneByUA(int i);
	@Query("select p from Participation p where p.thread.id = ?1")
	Collection<Participation> findManyByThreadId(Integer threadId);

	@Query("select p from Participation p where p.id = ?1")
	Participation findOneById(Integer id);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAutehnticatedByUserAccountUsername1(Integer username);

	@Query("select thr.authenticated from Thread thr where thr.id = ?1")
	Authenticated findCreatorByThreadId(Integer threadId);

	@Query("select p.thread.authenticated from Participation p where p.id = ?1")
	Authenticated findCreatorById(Integer id);

	@Query("select p.authenticated from Participation p where p.thread.id = ?1")
	Collection<Authenticated> findAuthenticatedByThreadId(Integer threadId);

	@Query("select ua from UserAccount ua where ua.username = ?1")
	UserAccount findUserAccountByUserAccoundUsername(String username);

	@Query("select thr from Thread thr where thr.id = ?1")
	acme.entities.messages.Thread findThreadByThreadId(Integer threadId);

	@Query("select p.authenticated from Participation p where p.id = ?1")
	Collection<Authenticated> findAuthenticatedById(Integer id);

	@Query("select a from Authenticated a where a.userAccount.username = ?1")
	Authenticated findAutehnticatedByUserAccountUsername(String username);

	@Query("select p.thread.id from Participation p where p.id = ?1")
	Integer findThreadIdById(Integer id);

}
