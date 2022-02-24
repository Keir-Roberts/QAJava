package main;

import java.util.Scanner;

public class menu {
	public static void doMenu() {
		while (true) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.println("Do you want to 1. create a person, 2. output all people or 3. search for a person?");
			int choice = Integer.parseInt(scan.nextLine());
			switch (choice) {
			case 1:
				System.out.println("What is their name?");
				String name = scan.nextLine();
				System.out.println("What is their age?");
				int age = Integer.parseInt(scan.nextLine());
				System.out.println("What is their job title?");
				String job = scan.nextLine();
				Person tempname = new Person(name, age, job);
				personList.addPeople(tempname);
				break;

			case 2:
				System.out.println(personList.people);
				break;

			case 3:
				System.out.println("Input the name of who you want to search for.");
				String searchName = scan.nextLine();
				System.out.println(personList.findPeople(searchName));
				break;
			default:
				System.out.println("Please input the number of the option you want.");
			}

			System.out.println("Do you want to return to menu or exit? y/n");
			char exit = scan.nextLine().charAt(0);
			if (Character.toLowerCase(exit) == 'y') {
				continue;
			} else if (Character.toLowerCase(exit) == 'n') {
				break;
			} else {
				System.out
						.println("Please input either y or n (if neither is detected, this will automatically exit).");
				char exit2 = scan.nextLine().charAt(0);
				if (Character.toLowerCase(exit2) == 'y') {
					continue;
				} else {
					break;
				}
			}
		}
	}
}
