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
}
