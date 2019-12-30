
package acme.features.auditor.job;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcement;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorJobListNotMineService implements AbstractListService<Auditor, Job> {

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
		result = this.repository.findManyNotMineByAuditorId(principal.getActiveRoleId());
		return result;
	}
}
