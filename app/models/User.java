package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionOfElements;

import play.db.jpa.Model;
import play.libs.Codec;

@Entity
public class User extends Model {
	public String username;
	public String email;
	public String password;
	public Date createdOn = new Date();

	@OneToOne(mappedBy = "user")
	public Ranking ranking;

	@OneToMany(mappedBy = "user")
	@OrderBy("createdOn DESC")
	public List<Game> games = new ArrayList<Game>();

	@Transient
	public Map<GameStatus, List<Game>> record;

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = Codec.hexSHA1(password);
	}

	public Map<GameStatus, List<Game>> getRecord() {
		if (record == null) {
			record = new TreeMap<GameStatus, List<Game>>();
			for (Game game : games) {
				if (record.get(game.status) == null)
					record.put(game.status, new ArrayList<Game>());
				record.get(game.status).add(game);
			}
		}
		return record;
	}

	public List<String> getRecordValues() {
		List<String> values = new ArrayList();
		for (List<Game> gameList : getRecord().values()) {
			values.add("" + gameList.size());
		}
		return values;
	}

	public String getGravatarHash() {
		if (email != null)
			return Codec.hexMD5(email);
		else
			return null;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof User) {
			User u = (User) other;
			return username.equals(u.username);
		}
		return false;
	}

	public boolean checkPassword(String password) {
		return this.password.equals(Codec.hexSHA1(password));
	}

	public static User connect(String username, String password) {
		return find("byUsernameAndPassword", username, Codec.hexSHA1(password)).first();
	}

	public static User findByUsername(String username) {
		return find("username", username).first();
	}
}
