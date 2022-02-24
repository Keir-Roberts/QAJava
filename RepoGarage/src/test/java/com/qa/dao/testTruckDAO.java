package com.qa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import com.qa.vehicles.Truck;

@RunWith(MockitoJUnitRunner.class)
public class testTruckDAO {
	TruckDAO TruckDAO = new TruckDAO();
	@Before
	public void resetcount() {
		Truck.setCount(0);
	}

	@Test
	public void testCreate() {
		// when
		TruckDAO TruckDAO = new TruckDAO();
		Truck mockTruck = new Truck(4, "Blue", 500);
		// then
		assertEquals(mockTruck, TruckDAO.create(mockTruck));

	}

	@Test
	public void testReadAll() {
		// when
		TruckDAO TruckDAO = new TruckDAO();
		List<Truck> allTruck = new ArrayList<Truck>();
		allTruck.clear();
		Truck mockTruck1 = new Truck(4, "Blue", 500);
		Truck mockTruck2 = new Truck(4, "Red", 500);
		mockTruck1.setId(1);
		mockTruck2.setId(2);
		allTruck.add(mockTruck1);
		allTruck.add(mockTruck2);
		System.out.println(allTruck);
		assertEquals(allTruck, TruckDAO.readAll());
	}

	@Test
	public void testRead() {
		TruckDAO TruckDAO = new TruckDAO();
		List<Truck> allTruck = new ArrayList<Truck>();
		Truck mockTruck1 = new Truck(4, "Blue", 500);
		Truck mockTruck2 = new Truck(4, "Red", 500);
		TruckDAO.create(mockTruck1);
		TruckDAO.create(mockTruck2);
		allTruck.add(mockTruck1);
		allTruck.add(mockTruck2);
		assertEquals(mockTruck1, TruckDAO.read(1));
	}

	@Test
	public void testDelete() {
		TruckDAO TruckDAO = new TruckDAO();
		List<Truck> allTruck = new ArrayList<Truck>();
		Truck mockTruck1 = new Truck(4, "Blue", 500);
		Truck mockTruck2 = new Truck(4, "Red", 500);
		TruckDAO.create(mockTruck1);
		TruckDAO.create(mockTruck2);
		allTruck.add(mockTruck1);
		allTruck.add(mockTruck2);
		assertTrue(TruckDAO.delete(mockTruck1.getId()));
	}
}

