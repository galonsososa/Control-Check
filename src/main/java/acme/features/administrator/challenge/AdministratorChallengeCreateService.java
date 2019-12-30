
package acme.features.administrator.challenge;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "goalBronze", "rewardBronze", "goalSilver", "rewardSilver", "goalGold", "rewardGold");

	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;
		result = new Challenge();
		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {
			Date now = new Date(System.currentTimeMillis() - 1);
			boolean deadlinePassed = entity.getDeadline().after(now);
			errors.state(request, deadlinePassed, "deadline", "administrator.challenge.error.deadline.passed");
		}

		if (!errors.hasErrors("rewardBronze")) {
			boolean moneyCurrencyMax = entity.getRewardBronze().getCurrency().equals("EUR") || entity.getRewardBronze().getCurrency().equals("EUROS") || entity.getRewardBronze().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "rewardBronze", "administrator.challenge.error.rewardBronze.currency");
		}

		if (!errors.hasErrors("rewardSilver")) {
			if (entity.getRewardBronze() != null) {
				boolean SilverGreaterBronze = entity.getRewardSilver().getAmount() > entity.getRewardBronze().getAmount();
				errors.state(request, SilverGreaterBronze, "rewardSilver", "administrator.challenge.error.rewardSilver.greater");
			}
			boolean moneyCurrencyMax = entity.getRewardSilver().getCurrency().equals("EUR") || entity.getRewardSilver().getCurrency().equals("EUROS") || entity.getRewardSilver().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "rewardSilver", "administrator.challenge.error.rewardSilver.currency");
		}

		if (!errors.hasErrors("rewardGold")) {
			if (entity.getRewardSilver() != null) {
				boolean GoldGreaterSilver = entity.getRewardGold().getAmount() > entity.getRewardSilver().getAmount();
				errors.state(request, GoldGreaterSilver, "rewardGold", "administrator.challenge.error.rewardGold.greater");
			}
			boolean moneyCurrencyMax = entity.getRewardGold().getCurrency().equals("EUR") || entity.getRewardGold().getCurrency().equals("EUROS") || entity.getRewardGold().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "rewardGold", "administrator.challenge.error.rewardGold.currency");

		}
	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {

		this.repository.save(entity);

	}

}
