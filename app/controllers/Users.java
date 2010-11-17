package controllers;

import models.User;
import play.mvc.*;

public class Users extends Application {

	public static void index() {
		render();
	}

	public static void show(String username) {
		User user = User.findByUsername(username);
		render(user);
	}

}
