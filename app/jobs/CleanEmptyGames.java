package jobs;

import java.util.List;

import models.Game;
import models.GameState;
import models.GameStatus;
import play.Logger;
import play.jobs.Job;
import play.jobs.On;

@On("0 * * * * ?")
public class CleanEmptyGames extends Job {

	public void doJob() {
		Logger.info("Starting cleanup on empty games");
		List<Game> games = Game.find("byStatus", GameStatus.Incomplete).fetch();
		for (Game game : games) {
			if (game.moves.size() == 0)
				game.delete();
		}
	}
}