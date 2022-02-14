package main;

public class coinsExercise {
public static void change(double cost, double amountPaid) {
	double remainder = amountPaid - cost;
	int n20 = 0;
	int n10 = 0;
	int n5 = 0;
	int n2 = 0;
	int n1 = 0;
	int n50p = 0;
	int n20p = 0;
	int n10p = 0;
	int n5p = 0;
	int n2p = 0;
	int n1p = 0;
	System.out.println("You are owed:");
	for(double a = remainder; a>20; a -= 20) {
		n20++;
		remainder -= 20;
	}
	System.out.println(n20 + " £20 notes");
	for(double b = remainder; b>10; b -= 10) {
		n10++;
		remainder -= 10;
	}
	System.out.println(n10 + " £10 notes");
	for(double c = remainder; c>5; c -=5) {
		n5++;
		remainder -= 5;
	}
	System.out.println(n5 + " £5 notes");
	for(double d = remainder; d>2; d -=2) {
		n2++;
		remainder -= 2;
	}
	System.out.println(n2 + " £2 coins");
	for(double e = remainder; e>1; e -=1) {
		n1++;
		remainder -= 1;
	}
	System.out.println(n1 + " £1 coins");
	for(double f = remainder; f>0.5; f -=0.5) {
		n50p++;
		remainder -= 0.5;
	}
	System.out.println(n50p + " 50p coins");
	for(double g = remainder; g>0.2; g -=0.2) {
		n20p++;
		remainder -= 0.2;
	}
	System.out.println(n20p + " 20p coins");
	for(double h = remainder; h>0.1; h -=0.1) {
		n10p++;
		remainder -= 0.1;
	}
	System.out.println(n10p + " 10p coins");
	for(double i = remainder; i>0.05; i -=0.05) {
		n5p++;
		remainder -= 0.05;
	}
	System.out.println(n5p + " 5p coins");
	for(double j = remainder; j>0.02; j -=0.02) {
		n2p++;
		remainder -= 0.02;
	}
	System.out.println(n2p + " 2p coins");
	if(remainder > 0) {
	for(double d = remainder; d>0.1; d -=0.01) {
		n1p++;
		remainder -= 0.01;
	}
	}
	System.out.println(n1p + " 1p coins");
}
public static void main(String[] args) {
	change(12.58, 50);
}
}
