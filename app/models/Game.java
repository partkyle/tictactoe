package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import play.Logger;
import play.db.jpa.Model;
import play.i18n.Messages;
import play.libs.Codec;

@Entity
@Table(name = "game")
public class Game extends Model {
	public String uuid = Codec.UUID();
	public Date createdOn = new Date();

	@Enumerated(EnumType.STRING)
	public GameStatus status = GameStatus.Incomplete;

	@ManyToOne
	public User user;

	@OneToMany(mappedBy = "game", cascade = { CascadeType.ALL })
	@OrderBy("createdOn")
	public List<Move> moves = new ArrayList<Move>();

	public Player[][] getState() {
		Player[][] gameState = { { null, null, null }, { null, null, null }, { null, null, null } };
		for (Move move : moves) {
			gameState[move.y][move.x] = move.player;
		}
		return gameState;
	}

	public boolean addMove(Move move) {
		if (moves.size() >= 9) {
			Logger.debug("The game is finished");
			return false;
		} else if (!moves.contains(move)) {
			moves.add(move);
			move.game = this;
			return true;
		} else {
			Logger.debug("Ignored duplicate move");
			return false;
		}
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Game) {
			Game g = (Game) other;
			return id.equals(g.id);
		}
		return false;
	}

	public static Game findByUuid(String uuid) {
		return find("byUuid", uuid).first();
	}

	@Override
	public String toString() {
		return Messages.get("game", status, createdOn);
	}

}
