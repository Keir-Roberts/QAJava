package main;

import static org.junit.Assert.assertEquals;
import org.junit.*;

public class FactorialTest {

	@Test
	public void testfindfactorial() {
		assertEquals("expected \"5!\"", "5!", Factorial.findfactorial(120));
	}
	@Test
	public void testfindfactorial1() {
		assertEquals("expected \"1!\"", "1!", Factorial.findfactorial(1));
	}
	@Test
	public void testfindfactorialnull() {
		assertEquals("expected ", "NONE", Factorial.findfactorial(150));
	}
}
