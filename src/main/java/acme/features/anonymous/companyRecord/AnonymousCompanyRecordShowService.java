
package acme.features.anonymous.companyRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousCompanyRecordShowService implements AbstractShowService<Anonymous, CompanyRecord> {

	// Internal state

	@Autowired
	AnonymousCompanyRecordRepository repository;


	//AbstractShowService<Authenticated, CompanyRecord< interface

	@Override
	public boolean authorise(final Request<CompanyRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<CompanyRecord> request, final CompanyRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "companyName", "sector", "ceoName", "description", "webSite", "phone", "email", "incorporated", "stars");
	}

	@Override
	public CompanyRecord findOne(final Request<CompanyRecord> request) {
		assert request != null;
		CompanyRecord result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
