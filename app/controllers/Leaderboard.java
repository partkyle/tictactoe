package controllers;

import java.util.List;

import models.Ranking;

public class Leaderboard extends Application {

	public static void show() {
		List<Ranking> rankings = Ranking.findAll();
		render(rankings);
	}

}
