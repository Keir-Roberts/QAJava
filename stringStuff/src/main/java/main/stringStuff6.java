package main;

public class stringStuff6 {
	public static boolean contains(String message, String string) {
	int i = 0;
	boolean output = false;
	for(i = 0; i+string.length() <= message.length() -1; i++) {
		String sub = message.substring(i, i+string.length());
		if(sub.equals(string)) {
			output = true;
		} else {
			continue;
		}
	}
	return output;
	}
	public static void main(String[] args) {
		String input1 = "This is a string used as an example.";
		String input2 = "Thi";
		System.out.println(contains(input1, input2));
	}
}