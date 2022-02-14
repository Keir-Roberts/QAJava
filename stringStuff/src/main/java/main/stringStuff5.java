package main;

public class stringStuff5 {
	public static void printVert(String sentence) {
		for(int i = sentence.length() -1; i >= 0; i--) {
			System.out.println(sentence.substring(i, i +1));
		}
	}
	public static void main(String[] args) {
		String input = "This is a string used as an example.";
		printVert(input);
	}
}
