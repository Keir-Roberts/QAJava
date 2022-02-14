package main;

public class stringStuff3 {
	;
	public static int numWords(String sentence) {
		if (sentence.equals("")) {
			return 0;
		} else {
			int count = 0;
			char space = ' ';
			for (int i = 0; i < sentence.length(); i++) {
				if (sentence.charAt(i) == (space)) {
					count++;
				}

			}
			return count + 1;
		}
	}

	public static void main(String[] args) {
		String input = "This is a string used as an example.";
		System.out.println(numWords(input));
	}
}
