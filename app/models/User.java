package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import play.db.jpa.GenericModel;
import play.libs.Codec;

@Entity
public class User extends GenericModel {
	@Id
	public String username;
	public String password;
	public Date createdOn = new Date();

	@OneToMany(mappedBy = "user")
	@OrderBy("createdOn")
	public List<Game> games = new ArrayList<Game>();

	@Transient
	Map<GameStatus, List<Game>> record;

	public User(String username, String password) {
		this.username = username;
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
