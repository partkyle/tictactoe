package models;

import java.util.Collection;
import java.util.Iterator;

public enum GameStatus {
	Incomplete("000000"), Win("00CC00"), Loss("FF0000"), Draw("DEDEDE");

	public String color;

	private GameStatus(String color) {
		this.color = color;
	}

	public static String getColorURL(Collection<GameStatus> statuses) {
		if (statuses != null && statuses.size() > 0) {
			Iterator<GameStatus> iter = statuses.iterator();
			String result = iter.next().color;
			while (iter.hasNext()) {
				result += "|" + iter.next().color;
			}
			return result;
		}
		return null;
	}
	
	public boolean isIncomplete() {
		return this == GameStatus.Incomplete;
	}
}
