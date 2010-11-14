package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Move extends Model {
	public int x;
	public int y;
}
