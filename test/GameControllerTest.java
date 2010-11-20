import java.util.List;

import models.Game;
import models.GameStatus;

import org.junit.Test;

import play.test.FunctionalTest;

public class GameControllerTest extends FunctionalTest {
	@Test
	public void testFindByStatus() {
		List<Game> games = Game.find("byStatus", GameStatus.Incomplete).fetch();
		assertTrue(games.size() > 0);
	}
}
