package model;

public class Game {
	
	private int score1;
	private int score2;
	private int counter;
	private boolean end_game;
	
	public Game() {
		//RMB: Lo score 1 è SEMPRE dell'utente che calcia per primo e il 2 di quello che calcia per secondo
		this.score1 = 0;
		this.score2 = 0;
		this.counter = 0;
		this.end_game = false;
	}
	
	public String tryShot(Shot shot) {
		String res="";
		if(counter > 10 || end_game == true) {
			res = "The game ended at "+score1+" - "+score2+". Do you want to play a new game?";
		}else {
			if(counter % 2 == 0) {
				counter++;
				if(shot.getShotResult()) {
					score1++;
					if(score1 > (5-counter/2)+score2)
						end_game = true;
					res = "Goal! Current score: "+score1+" - "+score2;
				}else {
					if(score2 > (5-counter/2)+score1)
						end_game = true;
					res = "Saved! Current score: "+score1+" - "+score2;
				}
			}else {
				counter++;
				if(shot.getShotResult()) {
					score2++;
					if(score2 > (5-counter/2)+score1)
						end_game = true;
					res = "Goal! Current score: "+score1+" - "+score2;
				}else {
					if(score1 > (5-counter/2)+score2)
						end_game = true;
					res = "Saved! Current score: "+score1+" - "+score2;
				}
			}
			if(counter > 10 || end_game == true) {
				res += "\nThe game ended at "+score1+" - "+score2+". Do you want to play a new game?";
			}
		}
		return res;
	}
}
