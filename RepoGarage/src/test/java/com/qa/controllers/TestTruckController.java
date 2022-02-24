package com.qa.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.dao.TruckDAO;
import com.qa.utils.UserInput;
import com.qa.vehicles.Car;
import com.qa.vehicles.Truck;

@RunWith(MockitoJUnitRunner.class)

public class TestTruckController {
	@Before
	public void resetcount() {
		Truck.setCount(0);
	}
	@Mock
	private TruckDAO truckDAO;
	@Mock
	private UserInput input;

	@InjectMocks
	private TruckController truckController;

	@Test
	public void testCreate() {
		int wheels = 6;
		String colour = "Red";
		int capacity = 300;
		Truck expected = new Truck(wheels, colour, capacity);
		expected.setId(2);
		when(input.getInt()).thenReturn(wheels, capacity);
		when(input.getString()).thenReturn(colour);
		when(truckDAO.create(expected)).thenReturn(expected);
		assertEquals(expected, truckController.create());
		verify(input, times(2)).getInt();
		verify(input, times(1)).getString();
		verify(truckDAO, times(1)).create(expected);
	}

	@Test
	public void testDelete() {
		List<Truck> allTruck = new ArrayList<Truck>();
		Truck mockTruck1 = mock(Truck.class);
		Truck mockTruck2 = mock(Truck.class);
		allTruck.add(mockTruck1);
		allTruck.add(mockTruck2);
		when(mockTruck1.toString()).thenReturn("Mock Car");
		when(mockTruck2.toString()).thenReturn("Mock Car");
		when(truckDAO.readAll()).thenReturn(allTruck);
		when(truckDAO.delete(1)).thenReturn(true);
		when(input.getInt()).thenReturn(1);

		assertTrue(truckController.delete());

		verify(truckDAO, times(1)).readAll();
		verify(truckDAO, times(1)).delete(1);
		verify(input, times(1)).getInt();
	}
	
	@Test
	public void testReadAll() {
		List<Truck> allTruck = new ArrayList<Truck>();
		Truck mockTruck1 = mock(Truck.class);
		Truck mockTruck2 = mock(Truck.class);
		allTruck.add(mockTruck1);
		allTruck.add(mockTruck2);
		when(mockTruck1.toString()).thenReturn("Mock Car");
		when(mockTruck2.toString()).thenReturn("Mock Car");
		when(truckDAO.readAll()).thenReturn(allTruck);
		assertEquals(allTruck, truckController.readAll());
	}
	
	@Test
	public void testUpdate() {
		List<Truck> allTruck = new ArrayList<Truck>();
		Truck mockTruck1 = mock(Truck.class);
		Truck mockTruck2 = mock(Truck.class);
		Truck oldTruck = new Truck(6, "Orange", 480);
		oldTruck.setId(1);
		allTruck.add(mockTruck1);
		allTruck.add(mockTruck2);
		int id = 1;
		int wheels = 6;
		String colour = "Red";
		int capacity = 300;
		Truck newTruck = new Truck(wheels, colour, capacity);
		newTruck.setId(id);
		when(truckDAO.read(id)).thenReturn(oldTruck);
		when(truckDAO.delete(id)).thenReturn(true);
		when(truckDAO.create(newTruck)).thenReturn(newTruck);
		when(truckDAO.readAll()).thenReturn(allTruck);
		when(mockTruck1.toString()).thenReturn("Mock Car");
		when(mockTruck2.toString()).thenReturn("Mock Car");
		when(input.getInt()).thenReturn(id, wheels, capacity);
		when(input.getString()).thenReturn(colour);
		assertEquals(newTruck, truckController.update());
	}
}
