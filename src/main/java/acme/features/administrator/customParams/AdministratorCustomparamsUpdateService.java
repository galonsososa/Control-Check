
package acme.features.administrator.customParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customparams.Customparams;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCustomparamsUpdateService implements AbstractUpdateService<Administrator, Customparams> {

	@Autowired
	AdministratorCustomparamsRepository repository;


	@Override
	public boolean authorise(final Request<Customparams> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Customparams> request, final Customparams entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Customparams> request, final Customparams entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWords", "spamThreshold");

	}

	@Override
	public Customparams findOne(final Request<Customparams> request) {
		assert request != null;

		Customparams result;
		result = this.repository.findOne();

		return result;
	}

	@Override
	public void validate(final Request<Customparams> request, final Customparams entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Customparams> request, final Customparams entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
