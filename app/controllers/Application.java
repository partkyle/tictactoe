package controllers;

import play.*;
import play.data.validation.Equals;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	public static void index() {
		if (getLoggedIn() != null)
			Users.show(getLoggedIn().id);
		else
			render();
	}

	public static void login() {
		render();
	}
	
	public static void logout() {
		session.clear();
		index();
	}

	public static void signup() {
		render();
	}

	public static void authenticate(String username, String password) {
		User user = User.findByUsername(username);
		if (user == null || !user.checkPassword(password)) {
			flash.error("Bad username or bad password");
			flash.put("username", username);
			login();
		}
		session.put("userId", user.id);
		Users.show(user.id);
	}

	public static void register(@Required @MinSize(5) String username, @Required @MinSize(5) String password, @Equals("password") String password2) {
		if (User.find("username", username).first() != null)
			validation.addError("username", "%s is already taken", username);
		if (validation.hasErrors()) {
			validation.keep();
			params.flash();
			flash.error("Please correct these errors !");
			signup();
		}
		new User(username, password).save();
		login();
	}

	public static long getLoggedInId() {
		long userId = 0;
		try {
			userId = Long.parseLong(session.get("userId"));
		} catch (NumberFormatException e) {}

		return userId;
	}

	public static User getLoggedIn() {
		return User.findById(getLoggedInId());
	}
}