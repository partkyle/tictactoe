package controllers;

import models.Game;
import models.GameState;
import models.GameStatus;
import models.Move;
import models.Player;
import models.User;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.With;
import util.TicTacToeAI;

public class GameController extends Application {

	public static void newGame() {
		Game game = new Game();
		User loggedIn = getLoggedIn();
		if (loggedIn != null)
			game.user = loggedIn;
		game.save();
		show(game.id);
	}

	public static void show(String gameId) {
		Game game = Game.findById(gameId);
		if (game == null) {
			if (getLoggedIn() != null) {
				Users.show(getLoggedInId());
			} else {
				Application.index();
			}
		}
		render(game);
	}

	public static void makeMove(String gameId, Move m) {
		Game game = Game.findById(gameId);
		GameState gameState = new GameState();
		if (game.user != null && !game.user.equals(getLoggedIn())) {
			gameState.validMove = false;
			gameState.message = Messages.get("movepermissions");
		} else if (TicTacToeAI.isStillPlaying(game.getState())) {
			if (game.addMove(m)) {
				gameState.moveLog.add(m.toString());
				if (TicTacToeAI.isStillPlaying(game.getState())) {
					// Computer's Move
					Move compMove = TicTacToeAI.findNextMove(game.getState());
					if (game.addMove(compMove))
						gameState.moveLog.add(compMove.toString());
				}
				game.save();
			} else {
				gameState.message = Messages.get("movetaken");
				gameState.validMove = false;
			}
			gameState.state = game.getState();
			gameState.stillPlaying = TicTacToeAI.isStillPlaying(gameState.state);
			if (!gameState.stillPlaying)
				gameState.winner = TicTacToeAI.getWinner(gameState.state);
		} else {
			gameState.stillPlaying = false;
			gameState.state = game.getState();
			gameState.winner = TicTacToeAI.getWinner(gameState.state);
		}

		if (!gameState.stillPlaying) {
			if (gameState.winner == null)
				game.status = GameStatus.Draw;
			else if (gameState.winner == Player.Computer)
				game.status = GameStatus.Loss;
			else if (gameState.winner == Player.Player)
				game.status = GameStatus.Win;

			game.save();
		}
		renderJSON(gameState);
	}

}
