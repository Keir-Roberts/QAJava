package com.qa.controllers;

import java.util.List;

import com.qa.dao.CarDAOSQL;
import com.qa.utils.UserInput;
import com.qa.vehicles.Car;

public class CarController implements GarageController<Car> {

	private CarDAOSQL carDAOSQL;
	private UserInput input;

	public CarController(CarDAOSQL carDAOSQL) {
		super();
		this.input = UserInput.getInstance();
		this.carDAOSQL = carDAOSQL;
	}

	public CarController(CarDAOSQL carDAOSQL, UserInput input) {
		super();
		this.input = input;
		this.carDAOSQL = carDAOSQL;
	}

	public Car create() {
		System.out.println("How many wheels does the car have?");
		int wheels = input.getInt();
		System.out.println("What colour is it?");
		String colour = input.getString();
		System.out.println("Manufacturer?");
		String maker = input.getString();
		System.out.println("How many horsepower?");
		int horsePower = input.getInt();

		Car newCar = new Car(wheels, colour, maker, horsePower);
		return carDAOSQL.create(newCar);
	}

	public boolean delete() {
		readAll();
		System.out.println("ID of the car you wish to delete?");
		int id = input.getInt();
		return carDAOSQL.delete(id);
	}

	public List<Car> readAll() {
		List<Car> all = carDAOSQL.readAll();
		for (Car car : all) {
			System.out.println(car);
		}
		return all;
	}

	public Car update() {
		readAll();
		System.out.println("ID of the car you wish to Update?");
		int id = input.getInt();
		System.out.println("How many wheels does the car have?");
		int wheels = input.getInt();
		System.out.println("What colour is it?");
		String colour = input.getString();
		System.out.println("Maker?");
		String maker = input.getString();
			
		System.out.println("horsePower? Kg");
		int horsePower = input.getInt();
		Car result = carDAOSQL.update(id, wheels, colour, maker, horsePower);
			return result;
	}
}