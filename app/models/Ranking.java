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
}
