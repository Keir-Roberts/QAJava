package main;

public class fizzBuzz {
	public static void fizz(int lowerLimit, int upperLimit) {
		for(int i = lowerLimit; i <= upperLimit; i++) {
			if((i%5 == 0) & (i%3 == 0)) {
				System.out.println("Fizzbuzz");	
			} else if(i%5 == 0) {
				System.out.println("Buzz");
			} else if(i%3 == 0) {
				System.out.println("Fizz");
			} else {
				System.out.println(i);
			}
		}
	}
	public static void main(String[] args) {
		fizz(1, 20);
	}
}
