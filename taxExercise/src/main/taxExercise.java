package main;

public class taxExercise {
	public static int taxBracket(double salary) {
		if(salary <15000) {
			return 0;
		} else if(salary <20000) {
			return 10;
		} else if(salary <30000) {
			return 15;
		} else if(salary <45000) {
			return 20;
		} else {
			return 25;
		}
	}
	
	public static double taxAmount(double salary) {
		double amount =(salary * (0.01 * taxBracket(salary)));
		System.out.println("you will pay a total of: £" + amount);
		return amount;
	}
	public static void main(String[] args) {
		System.out.println("Your tax bracket is: " + taxBracket(20004.95) + "%");
		taxAmount(20004.95);
	}
}
