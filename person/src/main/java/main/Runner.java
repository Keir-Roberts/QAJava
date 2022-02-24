package main;
public class Runner {
public static void main(String[] args) {
	Person Anna = new Person("Anna", 25, "Accountant");
	personList.addPeople(Anna);
	Person Bob = new Person("Bob", 46, "Builder");
	personList.addPeople(Bob);
	Person Clarice = new Person("Clarice", 36, "Concert Clarinettist");
	personList.addPeople(Clarice);
	Person David = new Person("David", 52, "Dance instructor");
	personList.addPeople(David);
	Person Ellouise = new Person("Ellouise", 19, "Egg Enthusiast");
	personList.addPeople(Ellouise);
	menu.doMenu();
}
}
