package main;

public class printNumbers {
public static void printToTen() {
	for (int i = 1; i <= 10; i++) {
		System.out.println(i);
	}
}
public static void printHundredtoThousand() {
	for (int i = 100; i < 1000; i++) {
		System.out.println(i);
	}
}
public static void wordTen() {
	for (int i = 1; i <= 10; i++) {
		System.out.println(numRead.NumRead(i));
	}
}
public static void wordHundred() {
	for (int i = 1; i <= 100; i++) {
		System.out.println(numRead.NumRead(i));
	}
}
public static void wordThousand() {
	for (int i = 1; i <= 1000; i++) {
		System.out.println(numRead.NumRead(i));
	}
}
public static void main(String[] args) {
	wordHundred();
}
}
