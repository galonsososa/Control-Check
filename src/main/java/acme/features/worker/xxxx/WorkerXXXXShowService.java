
package acme.features.worker.xxxx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.XXXX.XXXX;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerXXXXShowService implements AbstractShowService<Worker, XXXX> {

	@Autowired
	WorkerXXXXRepository repository;


	@Override
	public boolean authorise(final Request<XXXX> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<XXXX> request, final XXXX entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description", "moreInfo");

	}

	@Override
	public XXXX findOne(final Request<XXXX> request) {
		assert request != null;

		XXXX result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneByJobId(id);

		return result;
	}

}
