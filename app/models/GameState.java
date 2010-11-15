package models;

import java.util.ArrayList;
import java.util.List;

public class GameState {
	public Player[][] state;
	public Player winner;
	public boolean stillPlaying = true;
	public boolean validMove = true;
	public List<String> moveLog = new ArrayList<String>();
	public String message;
}
