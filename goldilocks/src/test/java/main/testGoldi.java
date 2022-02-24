package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class testGoldi {

	public static List<String> input = new ArrayList<String>();
	public static List<String> chairs = new ArrayList<String>();

	@BeforeClass
	public static void testClass() {
		goldiCheck goldi = new goldiCheck();
		assertTrue("unexpected output", goldi.getClass().getName().equals("main.goldiCheck"));
	}

	@BeforeClass
	public static void chairs() {

		chairs.add("297 90 53");
		chairs.add("66 110 78");
		chairs.add("257 113 98");
		chairs.add("276 191 12");
		chairs.add("280 129 22");
		chairs.add("163 163 12");
		chairs.add("193 193 45");
		chairs.add("153 153 65");
		chairs.add("107 107 71");
		chairs.add("137 137 87");
		chairs.add("40 40 98");
		chairs.add("127 127 45");
		chairs.add("146 146 98");
		chairs.add("197 197 10");
		chairs.add("59 59 100");
		chairs.add("124 124 12");
		chairs.add("59 59 54");
		chairs.add("54 54 98");
		chairs.add("119 119 87");
		chairs.add("121 121 32");
		chairs.add("189 189 65");
		chairs.add("108 108 97");
		chairs.add("21 21 64");
		chairs.add("18 18 31");
		chairs.add("90 90 54");
		chairs.add("52 52 65");
		chairs.add("129 129 87");
		chairs.add("181 181 98");
		chairs.add("123 123 65");
		chairs.add("132 132 21");
	}

	@After
	public void clear() {
		input.clear();
	}

	@Test
	public void test190() {
		input.add("256 114");
		for (String values : chairs) {
			input.add(values);
		}
		System.out.println(goldiCheck.check(input));
		assertEquals("Should be one value", "The only suitable chair is [3]", goldiCheck.check(input));
	}

	@Test
	public void test20() {
		input.add("20 10");
		for (String values : chairs) {
			input.add(values);
		}
		System.out.println(goldiCheck.check(input));
		assertEquals("Should be one value", "There are no suitable chairs", goldiCheck.check(input));
	}

	@Test
	public void testPass() {
		input.add("20 200");
		for (String values : chairs) {
			input.add(values);
		}
		System.out.println(goldiCheck.check(input));
		assertEquals("Should include all", "The suitable chairs are [2, 3, 9, 10, 11, 13, 15, 18, 19, 22, 27, 28]", goldiCheck.check(input));
	}
	
	@Test
	public void testLuminous() {
		input.add("20 200");
		for (String values : chairs) {
			input.add(values);
		}
		
	}
}
