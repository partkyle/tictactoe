package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;
import play.libs.Codec;

@Entity
public class User extends Model {
	public String username;
	public String password;

	public Date createdOn = new Date();

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
