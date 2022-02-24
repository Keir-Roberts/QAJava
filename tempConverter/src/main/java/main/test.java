package main;
import static org.junit.Assert.*;

import org.junit.*;

public class test {

@Test
public void test1() {
tempConverter testcon = new tempConverter();
//given
float expected = (float) 10.0;
//when
float actual = testcon.convertFahrenheitToCelsius(50);
//then
assertEquals(expected, actual, 0.001);
}

@Test
public void test2() {
	tempConverter testcon = new tempConverter();
	//given
	float expect = (float) 50;
	//when
	float actual = testcon.convertCelsiusToFahrenheit(10);
	//then
	assertEquals(expect, actual, 0.001);
	
}

@Test
public void test3() {
	tempConverter testcon = new tempConverter();
	//given
	float expect = (float) 280;
	//when
	float actual = testcon.convertCelsiusToKelvin(7);
	//then
	assertTrue("Expected to be 280", (actual <= expect + 0.01) & (actual >= expect - 0.01));
}
}
