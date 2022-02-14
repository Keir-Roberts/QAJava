package main;

public class checkString {
	public static boolean checkGiven(String input) {
		String given = "blob";
		int givenLength = given.length();
		boolean contains = false;
		if (input.substring(input.length() - givenLength).equals(given)) {
			contains = true;
		} else {
			for (int x = 0; x < (input.length() - givenLength); x++) {
				if (input.substring(x, (x + givenLength)).equals(given)) {
					contains = true;
				}
			}
		}
		return contains;
	}

	public static boolean checkSame(String input1, String input2) {
		if (input1 == input2) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(checkGiven("Mr. bolblob"));
		System.out.println(checkSame("Mr. blobby", "Mr. blobby"));
	}
}
