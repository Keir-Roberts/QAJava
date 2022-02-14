package main;

public class stringStuff2 {
	public static void main (String[] args) {
		String Str1 = "today it is sunny";
		String Str2 = "yesterday it was raining";
		String Str3 = Str1.toUpperCase();
		String Str4 = Str2.toUpperCase();
		System.out.println( Str3.substring(0,12)+ Str4.substring(Str4.length()-7, Str4.length() ));
	}
}
