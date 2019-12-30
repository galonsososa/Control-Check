
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamChecker;
import acme.entities.customparams.Customparams;
import acme.entities.jobs.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerDutyUpdateService implements AbstractUpdateService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		boolean result;
		int dutyId;
		Duty duty;
		Employer employer;
		Principal principal;

		dutyId = request.getModel().getInteger("id");
		duty = this.repository.findOneById(dutyId);
		employer = duty.getJob().getEmployer();
		principal = request.getPrincipal();

		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "percentage");

	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Spam validations
		SpamChecker c = new SpamChecker();
		Customparams configuration = this.repository.findCustomParams();

		if (!errors.hasErrors("title")) {
			Boolean isTitleSpam = c.check(configuration, entity.getTitle());
			errors.state(request, !isTitleSpam, "title", "employer.duty.error.string.spam");
		}

		if (!errors.hasErrors("description")) {
			Boolean isDescriptionSpam = c.check(configuration, entity.getDescription());
			errors.state(request, !isDescriptionSpam, "description", "employer.duty.error.string.spam");
		}
		//minimun time percetage 1%
		if (!errors.hasErrors("percentage")) {
			boolean onePerCent = entity.getPercentage() < 1;
			errors.state(request, !onePerCent, "percentage", "employer.duty.error.percentage.onePercent");
		}
	}

	@Override
	public void update(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
