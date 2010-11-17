package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

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

	public User(String username, String password) {
		this.username = username;
		this.password = Codec.hexSHA1(password);
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
