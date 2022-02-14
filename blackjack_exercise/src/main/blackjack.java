package main;

public class blackjack {
	public static int jackCheck(int score1, int score2) {
		int output;
		if((score1 > 21) && (score2 > 21)) {
			output = 0;
			return output; 
		} else if(score1 > 21) {
			output = score2;
			return output;
		} else if(score2 > 21) {
			output = score1;
			return output;
		} else if(score1 > score2) {
			output = score1;
			return output;
		} else if(score1 < score2) {
			output = score2;
			return output;
		} else {
			output = score1;
			return output;
		}
	}
}
