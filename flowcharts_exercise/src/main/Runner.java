package main;

import java.util.Scanner;

public class Runner {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Input your first number");
		int num1 = Integer.parseInt(scan.nextLine());
		System.out.println("Input your second number");
		int num2 = Integer.parseInt(scan.nextLine());
		System.out.println("Are you adding(a) or multiplying (m)?");
		boolean plusdivide = true;
		String pd = scan.nextLine();
		if (pd.startsWith("a")) plusdivide = true;
		else if(pd.startsWith("m")) plusdivide = false;
		System.out.println(flowcharts1.booMaths(num1, num2, plusdivide));
		
}
}

