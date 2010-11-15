package util;

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

	public static Player getWinner(Player[][] state) {
		// check horizontal wins
		int[] a = { 0, 1, 2 };
		for (int x : a) {
			if (state[x][0] != null && state[x][0] == state[x][1] && state[x][0] == state[x][2]) {
				return state[x][0];
			}
		}

		// check vertical wins
		for (int y : a) {
			if (state[0][y] != null && state[0][y] == state[1][y] && state[0][y] == state[2][y]) {
				return state[0][y];
			}
		}

		// check diagonal wins
		if (state[0][0] != null && state[0][0] == state[1][1] && state[0][0] == state[2][2]) {
			return state[0][0];
		}
		if (state[0][2] != null && state[0][2] == state[1][1] && state[0][2] == state[2][0]) {
			return state[0][2];
		}

		return null;
	}
}
