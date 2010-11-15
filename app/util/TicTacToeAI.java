package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.Move;
import models.Player;

public class TicTacToeAI {

	/*
	 * 3 basic actions:
	 * 1. Win if there is a space to win
	 * 2. Block if there is a space to block
	 * 3. move randomly as a last resort
	 */
	public static Move findNextMove(Player[][] state) {
		Move m = null;
		List<Move> moves = new ArrayList<Move>();

		// Find all empty spaces
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if (state[y][x] == null)
					moves.add(new Move(x, y));
			}
		}
		// Look for winning spaces
		m = findWinningSpot(state, moves, Player.Computer);

		if (m == null) {
			// Look for spaces where the Player is the winner
			m = findWinningSpot(state, moves, Player.Player);

			if (m == null) {
				// Last Resort - pick a random spot
				Random r = new Random(System.currentTimeMillis());
				int index = r.nextInt(moves.size());
				m = moves.get(index);
				m.player = Player.Computer;
			}
		}

		return m;
	}

	private static Move findWinningSpot(Player[][] state, List<Move> moves, Player player) {
		for (Move move : moves) {
			Player[][] stateClone = duplicateState(state);
			stateClone[move.y][move.x] = player;
			if (getWinner(stateClone) == player) {
				move.player = Player.Computer;
				return move;
			}
		}
		return null;
	}

	private static Player[][] duplicateState(Player[][] state) {
		Player[][] stateCopy = new Player[3][3];
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				stateCopy[y][x] = state[y][x];
			}
		}

		return stateCopy;
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
		// check vertical wins
		int[] a = { 0, 1, 2 };
		for (int x : a) {
			if (state[x][0] != null && state[x][0] == state[x][1] && state[x][0] == state[x][2]) {
				return state[x][0];
			}
		}

		// check horizontal wins
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
