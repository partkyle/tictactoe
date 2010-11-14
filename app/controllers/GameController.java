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
		System.out.println(String.format("Move(%d): (%d, %d)", gameId, m.x, m.y));
		Game game = Game.findById(gameId);
		m.game = game;
		m.save();
		game.moves.add(m);
		game.save();
	}
}
