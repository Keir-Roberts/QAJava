package morseTranslate;

public class MorseToEnglish {
public static String translate(String input) {
	MorseLibrary Library = new MorseLibrary();
	Library.fillLib();
	String[] morseChar = input.split(" ");
	String output = "";
	for(String i: morseChar) {
		output = output + Library.lib.get(i);
	}
	return output;
}
public static void main(String[] args) {
	System.out.println(translate(".--- .- ...- .- / .. ... / -.-. --- --- .-.. / --- -.-"));
}
}
