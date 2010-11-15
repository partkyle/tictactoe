package controllers;

import models.Game;
import models.Move;
import play.mvc.Controller;

public class GameController extends Controller {

	public static void index(long gameId) {
		Game game = Game.findById(gameId);
		render(game);
	}

	public static void newGame() {
		Game game = new Game().save();
		index(game.getId());
	}

	public static void makeMove(long gameId, Move m) {
		Game game = Game.findById(gameId);
		if (!game.moves.contains(m)) {
			game.addMove(m);
			game.save();
			renderJSON(game.getState());
		} else {
			m.valid = false;
			renderJSON(m);
		}
	}
}
