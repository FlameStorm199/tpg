package model;

import java.io.IOException;

public class Game {
	
	private int score1;
	private int score2;
	private int counter;
	private boolean end_game;
	
	public Game() {
		//The first player shooting is ALWAYS score1
		this.score1 = 0;
		this.score2 = 0;
		this.counter = 0;
		this.end_game = false;
	}
	
	public String tryShot(Shot shot) throws IOException{
		String res="";
		if(end_game) {
			res = "La partita e' terminata con un risultato di "+score1+" - "+score2;
		}else {
			if(counter < 10) {
				if(counter % 2 == 0) {
					counter++;
					if(shot.getShotResult()) {
						score1++;
						if(score1 > (5-counter/2)+score2)
							end_game = true;
						res = "Goal! Punteggio attuale: "+score1+" - "+score2;
					}else {
						if(score2 > (5-counter/2)+score1)
							end_game = true;
						res = "Parata! Punteggio attuale: "+score1+" - "+score2;
					}
				}else {
					counter++;
					if(shot.getShotResult()) {
						score2++;
						if(score2 > (5-counter/2)+score1)
							end_game = true;
						res = "Goal! Punteggio attuale: "+score1+" - "+score2;
					}else {
						if(score1 > (5-counter/2)+score2)
							end_game = true;
						res = "Parata! Punteggio attuale: "+score1+" - "+score2;
					}
				}
				
				if((counter == 10 && (score1 != score2)) || end_game) {
					res += "\nLa partita e' terminata con un risultato di "+score1+" - "+score2;
					end_game = true;
				}
			}else {
				res = tryShotTiebreak(shot);
			}
			

		}
		return res;
	}

	public int getScore1() {
		return score1;
	}

	public int getScore2() {
		return score2;
	}
	
	public int getShotNumber() {
		return (counter / 2);
	}
	
	public void endGameByForce() {
		this.end_game = true;
	}

	private String tryShotTiebreak(Shot shot) throws IOException{
		String res = "";
		if(counter % 2 == 0) {
			counter++;
			if(shot.getShotResult()) {
				score1++;
				res = "Goal! Punteggio attuale: "+score1+" - "+score2;
			}else {
				res = "Parata! Punteggio attuale: "+score1+" - "+score2;
			}
		}else {
			counter++;
			if(shot.getShotResult()) {
				score2++;
				res = "Goal! Punteggio attuale: "+score1+" - "+score2;
			}else {
				res = "Parata! Punteggio attuale: "+score1+" - "+score2;
			}
			
			if(score1 != score2) {
				res += "\nLa partita e' terminata con un punteggio di "+score1+" - "+score2;
				end_game = true;
			}	
		}
		
		return res;
	}

	public boolean ended() {
		return end_game;
	}
	
	
}
