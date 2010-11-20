package jobs;

import java.util.Calendar;
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
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -10);
		List<Game> games = Game.find("status = ? AND createdOn < ?", GameStatus.Incomplete, cal.getTime()).fetch();
		for (Game game : games) {
			if (game.moves.size() == 0)
				game.delete();
		}
	}
}