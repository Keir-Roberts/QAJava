package main;

public class runner {
public static void main(String[] args) {
	Arrays1.addNumArray();
	for(int digits:Arrays1.numArray) {
		if( OddOrEven.OddEven(digits)) {
			System.out.println(digits*digits*digits);
		} else {
			System.out.println(digits*digits);
		}
	}
}
}
