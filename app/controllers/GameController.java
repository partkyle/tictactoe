package controllers;

import play.mvc.*;

public class GameController extends Controller {

	public static void index() {
		render();
	}

	public static void makeMove(int x, int y) {
		System.out.println(String.format("Move: (%d, %d)", x, y));
	}
}
