package main;

public class Arrays1 {
	public static int[] numArray = new int[20];

	public static void addNumArray() {
		for (int i = 0; i < numArray.length; i++) {
			numArray[i] = i + 1;
			System.out.println(numArray[i]);
		}
	}
	public static void timesTenArray() {
		for (int j = 0; j < numArray.length; j++) {
			numArray[j] = 10*numArray[j];
			System.out.println(numArray[j]);
		}
	}
		public static void squareArray() {
			for (int digit: numArray) {
				int digitSquare = digit * digit;
				System.out.println(digitSquare);
		}
	}

	public static void main(String[] args) {
		addNumArray();
		timesTenArray();
		squareArray();
	}
}