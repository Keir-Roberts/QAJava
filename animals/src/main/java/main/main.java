package main;

import main.bird;
import main.feline;

@SuppressWarnings("unused")
public class main {

	public static void main(String[] args) {
		bird Harold = new bird("Parrot", "Harold", 58, 7, 2.5f, true);
		feline cat = new feline("Siamese", "Fluffy", 7, 5, false);
		System.out.println(Harold.colourful());
		System.out.println(cat.chonkvalue());
	}

}
