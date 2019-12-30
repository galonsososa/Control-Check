
package acme.features.administrator.customParams;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customparams.Customparams;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorCustomparamsListService implements AbstractListService<Administrator, Customparams> {

	@Autowired
	AdministratorCustomparamsRepository repository;


	@Override
	public boolean authorise(final Request<Customparams> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Customparams> request, final Customparams entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWords", "spamThreshold");

	}

	@Override
	public Collection<Customparams> findMany(final Request<Customparams> request) {
		assert request != null;
		Collection<Customparams> result;
		result = this.repository.findMany();
		return result;

	}

}
