package main;

public class stringStuff3 {
	;
	public static int numWords(String sentence) {
		String[] words = sentence.split(" ");
		int wordcount = 0;
		for (String word: words) {
			if (!word.equals("")) {
				wordcount ++;
			}
		}
		return wordcount;
	}

	public static void main(String[] args) {
		String input = " This is a test";
		System.out.println(numWords(input));
	}
}
