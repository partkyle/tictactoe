package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Ranking extends Model {
	public User user;
	public long wins;
	public long losses;
	public long draws;

	public Ranking(User user) {
		this.user = user;
	}
}
