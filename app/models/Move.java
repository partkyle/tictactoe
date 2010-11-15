package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import play.db.jpa.Model;

@Entity
public class Move extends Model {
	public int x;
	public int y;

	@Column(nullable = false)
	public Player player = Player.Player;

	@ManyToOne(optional = false)
	public Game game;

	public Move() {}

	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Move(int x, int y, Player player) {
		this.x = x;
		this.y = y;
		this.player = player;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Move) {
			Move m = (Move) other;
			return x == m.x && y == m.y;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("Game[%d] - Move[%s]: %d, %d", game.id, player, x, y);
	}
}
