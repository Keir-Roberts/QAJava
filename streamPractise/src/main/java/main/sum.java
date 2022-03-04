package main;

import java.util.Arrays;
import java.util.List;

public class sum {
	public static void main(String[] args) {
	    List<Integer> digits = Arrays.asList(9, 26, 27, 3,4,7,8,12);
	    Integer result =
	    		digits.stream()
	    		.reduce((a, b) -> a+b)
	    		.get();
	System.out.println(result);
}
}
