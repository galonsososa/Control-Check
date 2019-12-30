
package acme.forms;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Integer						totalNumberAnnouncements;
	Integer						totalNumberCompanyRecords;
	Integer						totalNumberInvestorRecords;

	Double						minRequestReward;
	Double						maxRequestReward;
	Double						avgRequestReward;
	Double						stdRequestReward;

	Double						minOfferReward;
	Double						maxOfferReward;
	Double						avgOfferReward;
	Double						stdOfferReward;

	List<List<String>>			numberOfCompaniesGroupedBySector;
	List<List<String>>			numberOfInvestorsGroupedBySector;

}
