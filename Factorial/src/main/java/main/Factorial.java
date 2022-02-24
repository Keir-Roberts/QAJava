package main;


public class Factorial {
	public static String findfactorial(int input) {
		if (input == 1)
			return "1!";
		int counterup = 0;
		float counterdown = input;
		for (int i = 1; counterdown >= i; i++) {
			counterdown = counterdown / i;
			System.out.println(counterdown);
			counterup++;
		}
		System.out.println(counterup);
		
		if (counterdown == 1)
			return (counterup + "!");
		else
			return "NONE";
	}
}
