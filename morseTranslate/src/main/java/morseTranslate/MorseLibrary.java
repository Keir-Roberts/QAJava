package morseTranslate;
import java.util.HashMap;
import java.util.Map;
public class MorseLibrary {
Map<String, String> lib = new HashMap<>();
public void fillLib() {
	lib.put("/", " ");
	lib.put("-----", "0");
	lib.put(".----", "1");
	lib.put("..---", "2");
	lib.put("...--", "3");
	lib.put("....-", "4");
	lib.put(".....", "5");
	lib.put("-....", "6");
	lib.put("--...", "7");
	lib.put("---..", "8");
	lib.put("----.", "9");
	lib.put(".-.-.-", ".");
	lib.put("--..--", ",");
	lib.put("..--..", "?");
	lib.put(".-", "A");
	lib.put("-...", "B");
	lib.put("-.-.", "C");
	lib.put("-..", "D");
	lib.put(".", "E");
	lib.put("..-.", "F");
	lib.put("--.", "G");
	lib.put("....", "H");
	lib.put("..", "I");
	lib.put(".---", "J");
	lib.put("-.-", "K");
	lib.put(".-..", "L");
	lib.put("--", "M");
	lib.put("-.", "N");
	lib.put("---", "O");
	lib.put(".--.", "P");
	lib.put("--.-", "Q");
	lib.put(".-.", "R");
	lib.put("...", "S");
	lib.put("-", "T");
	lib.put("..-", "U");
	lib.put("...-", "V");
	lib.put(".--", "W");
	lib.put("_.._", "X");
	lib.put("-.--", "Y");
	lib.put("--..", "Z");
}
}
