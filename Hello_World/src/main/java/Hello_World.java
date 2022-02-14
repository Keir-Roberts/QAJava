
public class Hello_World {
	public static String setMessage(String message) {
		return message;
	}
	
	public static void printMessage(String message1) {
		System.out.println(setMessage(message1));
	}
	public static void main(String[] args) {
		printMessage("Hello world!");
	}
	
}
