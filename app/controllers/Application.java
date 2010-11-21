package controllers;

import models.User;
import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.i18n.Messages;
import play.mvc.Before;
import play.mvc.Controller;

public class Application extends Controller {

	@Before
	static void before() {
		if (isLoggedIn()) {
			renderArgs.put("loggedIn", true);
			renderArgs.put("username", getLoggedInUser().username);
		}
	}

	public static void index() {
		if (isLoggedIn())
			Users.show(getLoggedInId());
		else
			render();
	}

	public static void logout() {
		if (isLoggedIn())
			flash.success(Messages.get("message.loggedout"));
		session.clear();
		index();
	}

	public static void signup() {
		render();
	}

	public static void authenticate(String username, String password) {
		User user = User.findByUsername(username);
		if (user == null || !user.checkPassword(password)) {
			flash.error("Bad username or password");
			flash.put("username", username);
			index();
		}
		session.put("username", user.username);
		Users.show(user.username);
	}

	public static void register(@Required @MinSize(4) String username, @Email String email, @Required @MinSize(4) String password, @Equals("password") String password2) {
		if (User.find("username", username).first() != null)
			validation.addError("username", "validation.unique", username);
		if (validation.hasErrors()) {
			validation.keep();
			params.flash();
			signup();
		}
		User user = new User(username, email, password).save();
		flash.success(Messages.get("message.newuser", username));
		session.put("username", user.username);
		Users.show(user.username);
	}

	protected static boolean isLoggedIn() {
		return getLoggedInUser() != null;
	}

	protected static String getLoggedInId() {
		return session.get("username");
	}

	protected static User getLoggedInUser() {
		return User.findByUsername(getLoggedInId());
	}
}