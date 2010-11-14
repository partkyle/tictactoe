package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Game extends Model {
	@OneToMany(mappedBy = "game", cascade = { CascadeType.ALL })
	public List<Move> moves = new ArrayList<Move>();

	public Player[][] getState() {
		Player[][] gameState = { { null, null, null }, { null, null, null }, { null, null, null } };
		for (Move move : moves) {
			gameState[move.x][move.y] = move.player;
		}
		return gameState;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Game) {
			Game g = (Game) other;
			return id == g.id;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Game " + id;
	}
}
