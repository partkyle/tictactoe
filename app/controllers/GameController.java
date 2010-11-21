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
		User loggedIn = getLoggedInUser();
		if (loggedIn != null)
			game.user = loggedIn;
		game.save();
		show(game.uuid);
	}

	public static void show(String uuid) {
		Game game = Game.findByUuid(uuid);
		if (game == null) {
			if (isLoggedIn()) {
				Users.show(getLoggedInId());
			} else {
				Application.index();
			}
		}
		render(game);
	}

	public static void makeMove(String uuid, Move m) {
		Game game = Game.findByUuid(uuid);
		GameState gameState = new GameState();
		if (game.user != null && !game.user.equals(getLoggedInUser())) {
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
