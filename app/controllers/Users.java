package controllers;

import models.User;
import play.mvc.*;

public class Users extends Application {

	public static void index() {
		render();
	}

	public static void show(long userId) {
		User user = User.findById(userId);
		render(user);
	}

}
