
package acme.features.administrator.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequests.AuditorRequest;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAuditorCreateService implements AbstractCreateService<Administrator, Auditor> {

	@Autowired
	private AdministratorAuditorRepository repository;


	@Override
	public boolean authorise(final Request<Auditor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Auditor> request, final Auditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Auditor> request, final Auditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
		int id;
		AuditorRequest ar;
		id = request.getModel().getInteger("id");
		ar = this.repository.findOneAuditorRequestById(id);
		entity.setFirm(ar.getFirm());
		entity.setStatement(ar.getStatement());

	}

	@Override
	public Auditor instantiate(final Request<Auditor> request) {
		assert request != null;

		Auditor result;
		UserAccount userAccount;
		AuditorRequest ar;
		int id = request.getModel().getInteger("id");
		ar = this.repository.findOneAuditorRequestById(id);

		userAccount = ar.getUserAccount();

		result = new Auditor();
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void validate(final Request<Auditor> request, final Auditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Auditor> request, final Auditor entity) {
		assert request != null;
		assert entity != null;

		int id = request.getModel().getInteger("id");
		AuditorRequest ar = this.repository.findOneAuditorRequestById(id);
		this.repository.delete(ar);
		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Auditor> request, final Response<Auditor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
