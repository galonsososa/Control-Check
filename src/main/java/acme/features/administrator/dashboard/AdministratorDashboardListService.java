
package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorDashboardListService implements AbstractListService<Administrator, Dashboard> {

	@Autowired
	AdministratroDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberAnnouncements", "totalNumberCompanyRecords", "totalNumberInvestorRecords", "minRequestReward", "maxRequestReward", "avgRequestReward", "stdRequestReward", "minOfferReward", "maxOfferReward",
			"avgOfferReward", "stdOfferReward", "numberOfCompaniesGroupedBySector", "numberOfInvestorsGroupedBySector");

	}

	@Override
	public Collection<Dashboard> findMany(final Request<Dashboard> request) {
		List<Dashboard> result = new ArrayList<>();
		Dashboard d = new Dashboard();
		Integer totalNumberOfAnnouncements = this.repository.totalNumberOfAnnouncements();
		Integer totalNumberOfCompanyRecords = this.repository.totalNumberOfCompanyRecords();
		Integer totalNumberOfInvestorRecords = this.repository.totalNumberOfInvestorRecords();

		Double[][] minMaxAvgStdFromRequests = this.repository.MinMaxAvgStdFromRequests();
		Double[][] minMaxAvgStdFromOffers = this.repository.MinMaxAvgStdFromOffers();

		List<List<String>> companiesBySector = this.repository.getNumberOfCampaniesBySector();
		List<List<String>> investorsBySector = this.repository.getNumberOfInvestorBySector();

		d.setTotalNumberAnnouncements(totalNumberOfAnnouncements);
		d.setTotalNumberCompanyRecords(totalNumberOfCompanyRecords);
		d.setTotalNumberInvestorRecords(totalNumberOfInvestorRecords);

		d.setMinRequestReward(minMaxAvgStdFromRequests[0][0]);
		d.setMaxRequestReward(minMaxAvgStdFromRequests[0][1]);
		d.setAvgRequestReward(minMaxAvgStdFromRequests[0][2]);
		d.setStdRequestReward(minMaxAvgStdFromRequests[0][3]);

		d.setMinOfferReward(minMaxAvgStdFromOffers[0][0]);
		d.setMaxOfferReward(minMaxAvgStdFromOffers[0][1]);
		d.setStdOfferReward(minMaxAvgStdFromOffers[0][2]);
		d.setAvgOfferReward((minMaxAvgStdFromOffers[0][3] + minMaxAvgStdFromOffers[0][4]) / 2);

		d.setNumberOfCompaniesGroupedBySector(companiesBySector);
		d.setNumberOfInvestorsGroupedBySector(investorsBySector);
		result.add(d);

		return result;
	}

}
