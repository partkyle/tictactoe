package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Ranking extends Model {

	public static double SCORE_WIN = 2;
	public static double SCORE_LOSS = 1;
	public static double SCORE_DRAW = 0.5;

	public long wins = 0;
	public long losses = 0;
	public long draws = 0;

	public long rank = 0;
	public double score = 0.0;

	@OneToOne
	@JoinColumn(name = "user_id")
	public User user;

	public Ranking(User user) {
		this.user = user;
	}

	public void calculateRank() {
		score = (wins * SCORE_WIN) + (draws * SCORE_DRAW) - (losses * SCORE_LOSS);
	}

	public List<Long> getData() {
		List<Long> result = new ArrayList<Long>();
		if (wins > 0)
			result.add(wins);
		if (losses > 0)
			result.add(losses);
		if (draws > 0)
			result.add(draws);

		return result;
	}

	public List<GameStatus> getLabels() {
		List<GameStatus> result = new ArrayList<GameStatus>();
		if (wins > 0)
			result.add(GameStatus.Win);
		if (losses > 0)
			result.add(GameStatus.Loss);
		if (draws > 0)
			result.add(GameStatus.Draw);

		return result;
	}
}
