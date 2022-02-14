package main;

public class runner {
	public static void main(String[] args) {
car aygo = new car("toyota", "aygo", "petrol", 110, 4, false);
aygo.garageStore();
motorbike swift = new motorbike("Suzuki", "fast", "Petrol", 180, 500, "sport", true);
swift.garageStore();
helicopter chopper = new helicopter("Apache", "Chopper", "oil", 300, 1000, 4, "brrrrrrrr");
chopper.garageStore();

garage.printGarage();
garage.repairCosts();
}
}