package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
import play.libs.Codec;

@Entity
public class User extends Model {
	public String username;
	public String password;

	public Date createdOn = new Date();

	@OneToMany(mappedBy = "user")
	public List<Game> games = new ArrayList<Game>();

	public User(String username, String password) {
		this.username = username;
		this.password = Codec.hexSHA1(password);
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
