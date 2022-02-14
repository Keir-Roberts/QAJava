package main;

public class stringStuff4 {
public static void printVert(String sentence) {
	for(int i = 0; i < sentence.length(); i++) {
		System.out.println(sentence.substring(i, i +1));
	}
}
public static void main(String[] args) {
	String input = "This is a string used as an example.";
	printVert(input);
}
}
