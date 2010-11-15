package controllers;

import models.Move;
import models.Player;

public class TicTacToeAI {
	public static Move findNextMove(Player[][] state) {
		Move m = new Move();
		m.x = 0;
		m.y = 0;
		m.player = Player.Computer;
		return m;
	}
}
