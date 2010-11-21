package controllers;

import models.User;
import play.data.validation.Email;
import play.data.validation.Equals;
import play.i18n.Messages;
import play.libs.Codec;

public class Users extends Application {

	public static void index() {
		render();
	}

	public static void show(String username) {
		User user = User.findByUsername(username);
		if (user == null)
			Application.index();
		render(user);
	}

	public static void edit(String username) {
		if (!username.equals(getLoggedInId()))
			forbidden("You don't have access to that user.");
		User user = User.findByUsername(username);
		if (user == null)
			Application.index();
		render(user);
	}

	public static void update(String username, @Email String email, String password, @Equals("password") String password2) {
		if (!username.equals(getLoggedInId()))
			forbidden("You don't have access to that user.");
		if (validation.hasErrors()) {
			validation.keep();
			flash.put("email", email);
			edit(username);
		}
		User user = User.findByUsername(username);
		user.email = email;
		if (password != null)
			user.password = Codec.hexSHA1(password);
		user.save();
		show(user.username);
	}

}
