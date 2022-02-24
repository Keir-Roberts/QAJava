package main;

import java.util.Scanner;
public class binaryConversion {
	public static int inIntBinary(String input) {
		if (input.equals(String.valueOf(Integer.parseInt(input)))) {
			if (Integer.parseInt(input) == 0) {
				return 0;
			} else {
				int counter = input.length() - 1;
				int value = 0;
				for (char ch : input.toCharArray()) {
					int temp = ch - 48;
					System.out.println(temp);
					value += temp * Math.pow(2, counter);
					counter -=1;
					}
				return value;

			}
		} else {
			return 0;
		}
	}
	public static float inDecBinary(String input) {
		if (input.contains(".")) {
			int breakpoint = input.indexOf('.');
			String in1 = input.substring(0, breakpoint);
			String in2 = input.substring(breakpoint + 1);
			float out1 = (float) inIntBinary(in1);
			float out2 = ((float) inIntBinary(in2));
			float output = Float.parseFloat(out1 + "." + out2);
			return output;
		} else {
			return inIntBinary(input);
		}
	}
	public static int outIntBinary(String input) {
		int intInput = Integer.parseInt(input);
		int counter = 0;
		for (int i = 0; Math.pow(2, i + 1) <= intInput; i++) {
			counter ++;
		}
		int remainder = intInput;
		String output = "";
		for ( int j = counter; j >= 0; j--) {
			if( Math.pow(2, j) <= remainder) {
				remainder -= Math.pow(2, j);
				output = output + 1;
			} else {
				output = output + 0;
			}
		}
		return Integer.parseInt(output);
	}
	public static String outDecBinary(String input) {
		if (input.contains(".")) {
			int breakpoint = input.indexOf('.');
			String in3 = input.substring(0, breakpoint);
			String in4 = input.substring(breakpoint + 1);
			String output = outIntBinary(in3) + "." + outIntBinary(in4);
			return output;
		} else {
			return outIntBinary(input) + "";
		}
	}
public static void main(String[] args) {
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	System.out.println("Input a number");
	String input = scan.nextLine();
	System.out.println(outDecBinary(input));
	
}
}