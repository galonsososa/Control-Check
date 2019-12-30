
package acme.features.authenticated.thread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.Thread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedThreadRepository extends AbstractRepository {

	@Query("select thr from Thread thr where thr.authenticated.id = ?1 or thr in (select p.thread from Participation p where p.authenticated.id = ?1)")
	Collection<Thread> findManyByAuthenticatedId(int authenticatedId);

	@Query("select thr.authenticated from Thread thr where thr.id = ?1")
	Authenticated findOwnerById(Integer id);

	@Query("select a from Authenticated a where a in (select p.authenticated from Participation p where p.thread.id = ?1) or a in (select thr.authenticated from Thread thr where thr.id = ?1)")
	Collection<Authenticated> findAuthenticatedsById(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int userId);

	@Query("select thr from Thread thr where thr.id = ?1")
	Thread findOneById(int id);

}
