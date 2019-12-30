
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.Message;
import acme.entities.messages.Thread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.thread.id = ?1")
	Collection<Message> findManyByThreadId(int threadID);

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select p.authenticated from Participation p where p.thread.id = ?1")
	Collection<Authenticated> findAuthenticatedsByThreadId(Integer threadId);

	@Query("select p.authenticated from Participation p where p.thread.id = (select m.thread.id from Message m where m.id = ?1)")
	Collection<Authenticated> findAuthenticatedsById(Integer id);

	@Query("select thr.authenticated from Thread thr where thr.id = ?1")
	Authenticated findCreatorByThreadId(Integer threadId);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedByAuthenticatedId(Integer authenticatedId);

	@Query("select thr from Thread thr where thr.id = ?1")
	Thread findThreadByThreadId(Integer threadId);

	@Query("select cp.spamWords from Customparams cp")
	String findSpamWords();

	@Query("select cp.spamThreshold from Customparams cp")
	String findSpamThreshold();

	@Query("select thr.authenticated from Thread thr where thr in (select m.thread from Message m where m.id = ?1)")
	Authenticated findCreatorById(Integer id);

}
