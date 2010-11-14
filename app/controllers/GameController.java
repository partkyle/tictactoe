package controllers;

import models.Move;
import play.mvc.Controller;

public class GameController extends Controller {

	public static void index() {
		render();
	}

	public static void makeMove(Move m) {
		System.out.println(String.format("Move: (%d, %d)", m.x, m.y));
	}
}
