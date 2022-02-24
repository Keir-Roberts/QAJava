package com.qa.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.dao.CarDAOSQL;
import com.qa.utils.UserInput;
import com.qa.vehicles.Car;

@RunWith(MockitoJUnitRunner.class)
public class TestCarController {

	@Mock
	private CarDAOSQL carDAOSQL;
	
	@Mock
	private UserInput input;
	
	@InjectMocks
	private CarController carController;
	
	@Test
	public void testCreate() {
		int wheels = 4;
		String colour = "Blue";
		String maker = "BMW";
		int horsePower = 270;
		Car expected = new Car(wheels, colour, maker, horsePower);
		when(input.getInt()).thenReturn(wheels, horsePower);
		when(input.getString()).thenReturn(colour, maker);
		when(carDAOSQL.create(expected)).thenReturn(expected);
		
		assertEquals(expected, carController.create());
		// Car controller makes the 2nd Vehicle with ID 2
		verify(input, times(2)).getInt();
		verify(input, times(2)).getString();
		verify(carDAOSQL, times(1)).create(expected);
	}
	
	@Test
	public void testDelete() {
		List<Car> allCars = new ArrayList<Car>();
		Car mockCar1 = mock(Car.class);
		Car mockCar2 = mock(Car.class);
		allCars.add(mockCar1);
		allCars.add(mockCar2);
		
		when(mockCar1.toString()).thenReturn("Mock Car");
		when(mockCar2.toString()).thenReturn("Mock Car");
		when(carDAOSQL.readAll()).thenReturn(allCars);
		when(carDAOSQL.delete(1)).thenReturn(true);
		when(input.getInt()).thenReturn(1);
		
		assertTrue(carController.delete());
		
		verify(carDAOSQL, times(1)).readAll();
		verify(carDAOSQL, times(1)).delete(1);
		verify(input, times(1)).getInt();
	}
	
	@Test
	public void testReadAll() {
		List<Car> allCars = new ArrayList<Car>();
		Car mockCar1 = mock(Car.class);
		Car mockCar2 = mock(Car.class);
		allCars.add(mockCar1);
		allCars.add(mockCar2);
		
		when(mockCar1.toString()).thenReturn("Mock Car");
		when(mockCar2.toString()).thenReturn("Mock Car");
		when(carDAOSQL.readAll()).thenReturn(allCars);
		
		assertEquals(allCars,carController.readAll());
		
		verify(carDAOSQL, times(1)).readAll();
	}
	
	@Test
	public void testUpdate() {
		List<Car> allCars = new ArrayList<Car>();
		Car mockCar1 = mock(Car.class);
		Car mockCar2 = mock(Car.class);
		Car oldCar = new Car(4,"Red","BMW",250);
		allCars.add(mockCar1);
		allCars.add(mockCar2);
		carDAOSQL.create(oldCar);
		
		int id = oldCar.getId();
		int wheels = 4;
		String colour = "Blue";
		String maker = "BMW";
		int horsePower = 270;
		Car newCar = new Car(wheels,colour,maker,horsePower);
		newCar.setId(id);
		when(carDAOSQL.read(id)).thenReturn(oldCar);
		when(carDAOSQL.delete(id)).thenReturn(true);
		when(mockCar1.toString()).thenReturn("Mock Car");
		when(mockCar2.toString()).thenReturn("Mock Car");
		when(carDAOSQL.readAll()).thenReturn(allCars);
		when(input.getInt()).thenReturn(id, wheels, horsePower);
		when(input.getString()).thenReturn(colour, maker);
		
		assertEquals(newCar, carController.update());
		
		verify(carDAOSQL, times(1)).readAll();
		verify(carDAOSQL, times(1)).read(id);
		verify(carDAOSQL, times(1)).delete(id);
		verify(carDAOSQL, times(1)).create(newCar);
		verify(input, times(3)).getInt();
		verify(input, times(2)).getString();
	}
	
	
}