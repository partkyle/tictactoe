package controllers;

import models.Game;
import models.GameState;
import models.Move;
import play.i18n.Messages;
import play.mvc.Controller;
import util.TicTacToeAI;

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
		GameState gameState = new GameState();
		if (game.addMove(m)) {
			gameState.moveLog.add(m.toString());
			// Computer's Move
			Move compMove = TicTacToeAI.findNextMove(game.getState());
			if (game.addMove(compMove))
				gameState.moveLog.add(compMove.toString());
			// Save the game state
			game.save();
			gameState.state = game.getState();
		} else {
			gameState.message = Messages.get("movetaken");
			gameState.validMove = false;
		}
		renderJSON(gameState);
	}
}
