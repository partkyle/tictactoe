package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Move extends Model {
	public int x;
	public int y;

	@ManyToOne(optional = false)
	public Game game;

	@Override
	public String toString() {
		return String.format("Move[%d]: %d, %d", game.id, x, y);
	}
}
