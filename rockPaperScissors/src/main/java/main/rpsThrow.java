package main;

import java.util.Random;

public class rpsThrow {
	private  Random rand = new Random();
	
	public outcome game(rps play1, rps play2) {
		if (play1 == play2) {
			return outcome.DRAW;
		} else if((
				play1 == rps.ROCK && play2 == rps.SCISSORS) 
				|| (play1 == rps.SCISSORS && play2 == rps.PAPER) 
				|| (play1 == rps.PAPER && play2 == rps.ROCK)) {
			return outcome.WIN;
		} else {
			return outcome.LOSE;
		}
			
	}
	public rps randRPS() {
		
		int i = rand.nextInt(3);

		if (i == 2) {
			return rps.ROCK;
		} else if (i == 1) {
			return rps.PAPER;
		} else {
			return rps.SCISSORS;
		}
	}
public String rpsGame(rps input ) {
	
	rps opponent = randRPS();
	outcome output = game(input, opponent);
	return "The opponent played " + opponent + " so you " + output;
}
}