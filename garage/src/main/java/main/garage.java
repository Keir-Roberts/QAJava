package main;

import java.util.ArrayList;
import java.util.List;

public class garage  {
	public static List<Object> inGarage = new ArrayList<Object>();

	public static void printGarage() {
		System.out.println(inGarage);
	}

	public static void repairCosts() {
		for (Object vehicle : inGarage) {
			float bill;
			if (vehicle.getClass().getName() == "main.car") {
				if (((main.car) vehicle).isAutomatic()) {
					bill = ((float) 25 * ((main.car) vehicle).getSeatNum()) + 99.99f;
				} else {
					bill = ((float) 20 * ((main.car) vehicle).getSeatNum()) + 99.99f;
				}
				System.out.println(vehicle + " has a repair cost of £" + bill);
			} else if (vehicle.getClass().getName() == "main.motorbike") {
				if (((main.motorbike) vehicle).isTurboCharge()) {
					bill = ((float) 0.5 * ((main.motorbike) vehicle).getMileage() + 59.99f);

				} else {
					bill = ((float) 0.25 * ((main.motorbike) vehicle).getMileage() + 59.99f);
				}
				System.out.println(vehicle + " has a repair cost of £" + bill);
			} else if (vehicle.getClass().getName() == "main.helicopter") {
				bill = ((float) (((main.helicopter) vehicle).getMaxAltitude()) / 10)
						* ((float) ((main.helicopter) vehicle).getSeats());
				System.out.println(vehicle + " has a repair cost of £" + bill);
			} else {
				System.out.println(vehicle + " was not what I expected...");
			}
		}
	}
	public void addGarage(Object vehicle) {
		if (vehicle instanceof main.vehicles) {
			garage.inGarage.add(vehicle);
		} else {
			System.out.println("Must be a vehicle");
		}
	}

	public void removeGarage(int ID) {
		garage.inGarage.remove(ID);
	}

	public void removeGarage(Object vehicle) {
		int id = garage.inGarage.indexOf(vehicle);
		garage.inGarage.remove(id);

	}

	public float fix(int ID) {
		Object vehicle = garage.inGarage.get(ID);
		float bill;
		if (vehicle.getClass().getName() == "main.car") {
			if (((main.car) vehicle).isAutomatic()) {
				bill = ((float) 25 * ((main.car) vehicle).getSeatNum()) + 99.99f;
			} else {
				bill = ((float) 20 * ((main.car) vehicle).getSeatNum()) + 99.99f;
			}
			return bill;
		} else if (vehicle.getClass().getName() == "main.motorbike") {
			if (((main.motorbike) vehicle).isTurboCharge()) {
				bill = ((float) 0.5 * ((main.motorbike) vehicle).getMileage() + 59.99f);

			} else {
				bill = ((float) 0.25 * ((main.motorbike) vehicle).getMileage() + 59.99f);
			}
			return bill;
		} else if (vehicle.getClass().getName() == "main.helicopter") {
			bill = ((float) (((main.helicopter) vehicle).getMaxAltitude()) / 10)
					* ((float) ((main.helicopter) vehicle).getSeats());
			return bill;
		} else {
			return 0f;
		}

	}

	public float fix(Object vehicle) {
		float bill;
		if (vehicle.getClass().getName() == "main.car") {
			if (((main.car) vehicle).isAutomatic()) {
				bill = ((float) 25 * ((main.car) vehicle).getSeatNum()) + 99.99f;
			} else {
				bill = ((float) 20 * ((main.car) vehicle).getSeatNum()) + 99.99f;
			}
			return bill;
		} else if (vehicle.getClass().getName() == "main.motorbike") {
			if (((main.motorbike) vehicle).isTurboCharge()) {
				bill = ((float) 0.5 * ((main.motorbike) vehicle).getMileage() + 59.99f);

			} else {
				bill = ((float) 0.25 * ((main.motorbike) vehicle).getMileage() + 59.99f);
			}
			return bill;
		} else if (vehicle.getClass().getName() == "main.helicopter") {
			bill = ((float) (((main.helicopter) vehicle).getMaxAltitude()) / 10)
					* ((float) ((main.helicopter) vehicle).getSeats());
			return bill;
		} else {
			return 0f;
		}

	}

	public void emptyGarage() {
		inGarage.clear();
	}

	public void emptyType(String vehicleType) {
		for (Object vehicle : inGarage) {
			if (vehicle.getClass().getName() == "main." + vehicleType) {
				inGarage.remove(vehicle);
			}
		}
	}
}
