
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcement;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorJobListMineService implements AbstractListService<Auditor, Job> {

	// Internal state

	@Autowired
	AuditorJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
	}
	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "moreInfo");
	}

	@Override
	public Collection<Job> findMany(final Request<Job> request) {
		assert request != null;
		Principal principal;
		principal = request.getPrincipal();
		Collection<Job> result;
		result = this.repository.findManyMineByAuditorId(principal.getActiveRoleId());
		return result;
	}
}
