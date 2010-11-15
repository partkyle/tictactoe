package util;

import java.util.List;
import java.util.Random;

import models.Game;
import models.Move;
import models.Player;

public class TicTacToeAI {
	public static Move findNextMove(Player[][] state) {
		Move m = null;
		while (m == null) {
			Random r = new Random();
			m = new Move(r.nextInt(3), r.nextInt(3), Player.Computer);
			if (state[m.y][m.x] != null)
				m = null;
		}

		return m;
	}

	public static boolean isStillPlaying(Player[][] state) {
		int count = 0;
		for (Player[] rows : state) {
			for (Player player : rows) {
				if (player != null)
					count++;
			}
		}
		return count < 9 && getWinner(state) == null;
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
