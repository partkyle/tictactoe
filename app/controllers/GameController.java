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
		if (!game.moves.contains(m)) {
			game.addMove(m);

			// Computer's Move
			Move compMove = TicTacToeAI.findNextMove(game.getState());
			game.addMove(compMove);

			// Save the game state
			game.save();
			GameState gameState = new GameState();
			gameState.state = game.getState();
			gameState.moveLog.add(m.toString());
			gameState.moveLog.add(compMove.toString());
			renderJSON(gameState);
		} else {
			GameState gameState = new GameState();
			gameState.message = Messages.get("movetaken");
			gameState.validMove = false;
			renderJSON(gameState);
		}
	}
}
