package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Ranking extends Model {
	public long wins = 0;
	public long losses = 0;
	public long draws = 0;

	@OneToOne
	@JoinColumn(name = "user_username")
	public User user;

	public Ranking(User user) {
		this.user = user;
	}

	public long getRank() {
		if (wins == 0 && losses == 0 && draws == 0)
			return 0;
		return (long) (((float) wins / (wins + losses + draws)) * 100);
	}
}
