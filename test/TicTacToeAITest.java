import models.Player;

import org.junit.Test;

import play.test.FunctionalTest;
import util.TicTacToeAI;

public class TicTacToeAITest extends FunctionalTest {

	@Test
	public void testWinner() {
		/*
		 * P P P
		 * C P  
		 * C C  
		 */
		Player[][] state1 = { { Player.Player, Player.Player, Player.Player }, { Player.Computer, Player.Player, null }, { Player.Computer, Player.Computer, null } };
		Player winner1 = TicTacToeAI.getWinner(state1);
		assertEquals(Player.Player, winner1);
		assertFalse(TicTacToeAI.isStillPlaying(state1));

		/*
		 * C P  
		 * P P P
		 * C C  
		 */
		Player[][] state2 = { { Player.Computer, Player.Player, null }, { Player.Player, Player.Player, Player.Player }, { Player.Computer, Player.Computer, null } };
		Player winner2 = TicTacToeAI.getWinner(state2);
		assertEquals(Player.Player, winner2);
		assertFalse(TicTacToeAI.isStillPlaying(state2));

		/*
		 *   C P
		 *   C P
		 * P C  
		 */
		Player[][] state3 = { { null, Player.Computer, Player.Player }, { null, Player.Computer, Player.Player }, { Player.Player, Player.Computer, null } };
		Player winner3 = TicTacToeAI.getWinner(state3);
		assertEquals(Player.Computer, winner3);
		assertFalse(TicTacToeAI.isStillPlaying(state3));

		/*
		 * C P  
		 * C P  
		 * C   P
		 */
		Player[][] state4 = { { Player.Computer, Player.Player, Player.Computer }, { Player.Computer, Player.Player, null }, { Player.Computer, null, Player.Player } };
		Player winner4 = TicTacToeAI.getWinner(state4);
		assertEquals(Player.Computer, winner4);
		assertFalse(TicTacToeAI.isStillPlaying(state4));

		/*
		 * P P  
		 * C P  
		 * C   P
		 */
		Player[][] state5 = { { Player.Player, Player.Player, Player.Computer }, { Player.Computer, Player.Player, null }, { Player.Computer, null, Player.Player } };
		Player winner5 = TicTacToeAI.getWinner(state5);
		assertEquals(Player.Player, winner5);

		/*
		 * P P C
		 * C C  
		 * C   P
		 */
		Player[][] state6 = { { Player.Player, Player.Player, Player.Computer }, { Player.Computer, Player.Computer, null }, { Player.Computer, null, Player.Player } };
		Player winner6 = TicTacToeAI.getWinner(state6);
		assertEquals(Player.Computer, winner6);
		assertFalse(TicTacToeAI.isStillPlaying(state6));
	}
}
