package main;

public class repeatingNumbersExercise {
public static void repeat() {
	for(int i = 0; i<=10; i++) {
		for(int a = 0; a<i; a++) {
			System.out.println(i);
		}
	}
}
public static void main(String[] args) {
	repeat();
}
}