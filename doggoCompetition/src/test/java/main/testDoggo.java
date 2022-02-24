package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;

public class testDoggo {
	@Before
	public void testClass() {
	Doggo dog = new Doggo();
	 assertTrue("unexpected output", dog.getClass().getName().equals("main.Doggo"));
	}
	@Test
	public void testInvalidlessz() {
		assertTrue("unexpected output", Doggo.dogCheck(0).contains("Invalid Input"));
	}

	@Test
	public void testInvalidOverH() {
		assertTrue("unexpected output", Doggo.dogCheck(101).contains("Invalid Input"));
	}

	@Test
	public void testSize() {
		assertEquals("unexpected output", Doggo.dogCheck(1).size(), 99);
	}

	@Test
	public void testXst() {
		assertEquals("invalid suffix with 1", Doggo.dogCheck(100).get(20), "21st");
	}

	@Test
	public void testXrd() {
		assertEquals("invalid suffix with 3", Doggo.dogCheck(100).get(22), "23rd");
	}

	@Test
	public void testXnd() {
		assertEquals("invalid suffix with 2", Doggo.dogCheck(100).get(21), "22nd");
	}

	@Test
	public void testXth() {
		assertEquals("invalid suffix with th", Doggo.dogCheck(100).get(23), "24th");
	}

	@Test
	public void testContains() {
		assertFalse("Should not include place", Doggo.dogCheck(34).contains("34th"));
	}
	
	@Test
	public void testAfterRmv() {
		assertEquals("invalid place", Doggo.dogCheck(21).get(21), "23rd");
	}	

}
