package main;

public class calculator_exercise {
	public static void addition(double num1, double num2) {
		double result = num1 + num2;
		System.out.println( num1 + " + " + num2 + " = " + result); 
		}
	
	public static void subtraction(int num1, int num2) {
		int result = num1 - num2;
		System.out.println(num1 + " - " + num2 + " = " + result);
	}
	
	public static void division(double num1, double num2) {
		if(num1 < num2) {
			double result = num1 / num2;
			System.out.println( num1 + "/" + num2 + " = " + result);
		}
		
		else {
			System.out.println( "The division cannot be performed on top-heavy fractions");
		}
	}
	
	public static void main(String[] args) {
		division(8,6);
	}
}
