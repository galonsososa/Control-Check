
package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratroDashboardRepository extends AbstractRepository {

	@Query("select count(s) from Announcement s")
	Integer totalNumberOfAnnouncements();
	@Query("select count (s) from CompanyRecord s")
	Integer totalNumberOfCompanyRecords();
	@Query("select count (s) from Investor s")
	Integer totalNumberOfInvestorRecords();

	@Query("select min(s.reward.amount),max(s.reward.amount),avg(s.reward.amount),stddev(s.reward.amount) from Request s where s.deadline > CURRENT_DATE ")
	Double[][] MinMaxAvgStdFromRequests();

	@Query("select min(s.minMoney.amount),max(s.maxMoney.amount),stddev(s.minMoney.amount),avg(s.minMoney.amount),avg(s.maxMoney.amount) from Offer s where s.deadline > CURRENT_DATE ")
	Double[][] MinMaxAvgStdFromOffers();

	@Query("select s.sector,count(s) from CompanyRecord s group by s.sector ")
	List<List<String>> getNumberOfCampaniesBySector();

	@Query("select s.sector,count(s) from Investor s group by s.sector ")
	List<List<String>> getNumberOfInvestorBySector();

	@Query("select count(j)*1.0 / (select count(t) from Job t) from Job j where j.challengeDescription!='null'")
	Double ratioJobsWithChallenge();

	@Query("select count(a)*1.0 / (select count(t) from Application t) from Application a where a.answer!='null'")
	Double ratioOfJobsWithAnswer();

	@Query("select count(a)*1.0 / (select count(t) from Application t) from Application a where a.password!='null'")
	Double ratioOfApplicationsWithPassword();

}
