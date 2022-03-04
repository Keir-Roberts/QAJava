package userInput;

import java.util.Scanner;

public class userInput {
		
		private Scanner scan = new Scanner(System.in);
		private static userInput instance = null;
		
		private userInput() {
			
		}
		
		public String getString() {
			return scan.nextLine();
		}

		public int getInt() {
			int value;
			while (true) {
				try {
					System.out.println("Please type an Int");
					value = Integer.parseInt(scan.nextLine());
					return value;
				} catch (NumberFormatException exception) {
					exception.printStackTrace();
				}
			}
		}

		public static userInput getInstance() {
			if(instance == null) {
				instance = new userInput();
			}
			return instance;
		}
}
