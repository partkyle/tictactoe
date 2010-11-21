package controllers;

import java.util.List;

import models.Ranking;

public class Leaderboard extends Application {

	public static void index() {
		show(1);
	}

	public static void show(Integer page) {
		List<Ranking> rankings;
		Long count = Ranking.count();
		rankings = Ranking.find("order by rank").from((page - 1) * pageSize).fetch(pageSize);
		render(rankings, count, page);
	}

}
