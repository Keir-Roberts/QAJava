package main;

public class flowcharts4 {
	public static void flow() {
		for (int A = 100; A <= 200; A++) {
			if (A % 2 == 0) {
				System.out.println("-");
			} else {
				System.out.println("*");
			}
		}
	}

	public static void main(String[] args) {
		flow();
	}
}
