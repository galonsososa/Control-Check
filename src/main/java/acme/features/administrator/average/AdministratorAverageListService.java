
package acme.features.administrator.average;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorAverageListService implements AbstractListService<Administrator, Employer> {

	// Internal state

	@Autowired
	AdministratorAverageRepository repository;


	//AbstractListService<Authenthicated, Employer> interface

	@Override
	public boolean authorise(final Request<Employer> request) {
		assert request != null;
		return true;
	}
	@Override
	public void unbind(final Request<Employer> request, final Employer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "moment", "title");
	}

	@Override
	public Collection<Employer> findMany(final Request<Employer> request) {
		assert request != null;
		Collection<Employer> result;
		result = this.repository.findManyAll();
		return result;
	}
}
