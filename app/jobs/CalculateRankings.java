package jobs;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import models.Game;
import models.GameStatus;
import models.Ranking;
import models.User;
import play.Logger;
import play.jobs.Job;
import play.jobs.On;

@On("0 * * * * ?")
public class CalculateRankings extends Job {

	public void doJob() {
		Logger.info("Starting ranking calculation");
		List<User> users = User.findAll();
		for (User user : users) {
			Ranking rank = Ranking.find("byUser", user).first();
			if (rank == null)
				rank = new Ranking(user);

			Map<GameStatus, List<Game>> record = user.getRecord();
			if (record.get(GameStatus.Win) != null)
				rank.wins = record.get(GameStatus.Win).size();
			if (record.get(GameStatus.Loss) != null)
				rank.losses = record.get(GameStatus.Loss).size();
			if (record.get(GameStatus.Draw) != null)
				rank.draws = record.get(GameStatus.Draw).size();

			rank.save();
		}
	}
}