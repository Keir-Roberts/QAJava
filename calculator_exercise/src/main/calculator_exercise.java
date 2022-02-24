package main;
import java.util.Scanner;
public class calculator_exercise {
	public static void addition(double num1, double num2) {
		double result = num1 + num2;
		System.out.println( num1 + " + " + num2 + " = " + result); 
		}
	
	public static void subtraction(double num1, double num2) {
		double result = num1 - num2;
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
	public static void scanCalculate() {
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Choose either 'addition', 'subtraction' or 'division'.");
			String function = scan.nextLine();
			switch(function) {
				case "addition":
					System.out.println("Input your first number!");
					double add1 = Double.parseDouble(scan.nextLine());
					System.out.println("Input your second number!");
					double add2 = Double.parseDouble(scan.nextLine());
					addition(add1, add2);
					break;
				case "subtraction":
					System.out.println("Input your first number!");
					double sub1 = Double.parseDouble(scan.nextLine());
					System.out.println("Input your second number!");
					double sub2 = Double.parseDouble(scan.nextLine());
					subtraction(sub1, sub2);
					break;
				case "division":
					System.out.println("Input your first number!");
					double div1 = Double.parseDouble(scan.nextLine());
					System.out.println("Input your second number!");
					double div2 = Double.parseDouble(scan.nextLine());
					division(div1, div2);
					break;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		scanCalculate();
	}
}
