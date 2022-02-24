package com.qa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import com.qa.vehicles.Car;

@RunWith(MockitoJUnitRunner.class)
public class testCarDAO {
	CarDAO carDAO = new CarDAO();
	@Before
	public void resetcount() {
		Car.setCount(0);
	}

	@Test
	public void testCreate() {
		// when
		CarDAO carDAO = new CarDAO();
		Car mockCar = new Car(4, "Blue", "Fiat", 500);
		// then
		assertEquals(mockCar, carDAO.create(mockCar));

	}

	@Test
	public void testReadAll() {
		// when
		CarDAO carDAO = new CarDAO();
		List<Car> allCar = new ArrayList<Car>();
		allCar.clear();
		Car mockCar1 = new Car(4, "Blue", "Fiat", 500);
		Car mockCar2 = new Car(4, "Red", "Honda", 500);
		mockCar1.setId(1);
		mockCar2.setId(2);
		allCar.add(mockCar1);
		allCar.add(mockCar2);
		System.out.println(allCar);
		assertEquals(allCar, carDAO.readAll());
	}

	@Test
	public void testRead() {
		CarDAO carDAO = new CarDAO();
		List<Car> allCar = new ArrayList<Car>();
		Car mockCar1 = new Car(4, "Blue", "Fiat", 500);
		Car mockCar2 = new Car(4, "Red", "Honda", 500);
		carDAO.create(mockCar1);
		carDAO.create(mockCar2);
		allCar.add(mockCar1);
		allCar.add(mockCar2);
		assertEquals(mockCar1, carDAO.read(1));
	}

	@Test
	public void testDelete() {
		CarDAO carDAO = new CarDAO();
		List<Car> allCar = new ArrayList<Car>();
		Car mockCar1 = new Car(4, "Blue", "Fiat", 500);
		Car mockCar2 = new Car(4, "Red", "Honda", 500);
		carDAO.create(mockCar1);
		carDAO.create(mockCar2);
		allCar.add(mockCar1);
		allCar.add(mockCar2);
		assertTrue(carDAO.delete(mockCar1.getId()));
	}
}
