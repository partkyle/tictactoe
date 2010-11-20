package controllers;

import play.*;
import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	@Before
	static void before() {
		if (getLoggedIn() != null) {
			renderArgs.put("loggedIn", true);
			renderArgs.put("username", getLoggedIn().username);
		}
	}

	public static void index() {
		if (getLoggedIn() != null)
			Users.show(getLoggedInId());
		else
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
			index();
		}
		session.put("username", user.username);
		Users.show(user.username);
	}

	public static void register(@Required @MinSize(5) String username, @Email String email, @Required @MinSize(5) String password, @Equals("password") String password2) {
		if (User.find("username", username).first() != null)
			validation.addError("username", "%s is already taken", username);
		if (validation.hasErrors()) {
			validation.keep();
			params.flash();
			flash.error("Please correct these errors !");
			signup();
		}
		User user = new User(username, email, password).save();
		session.put("username", user.username);
		Users.show(user.username);
	}

	public static String getLoggedInId() {
		return session.get("username");
	}

	public static User getLoggedIn() {
		return User.findByUsername(getLoggedInId());
	}
}