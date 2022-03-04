package main;

import java.util.Arrays;
import java.util.List;

public class max {
	public static void main(String[] args) {
	    List<Integer> digits = Arrays.asList(9, 27, 3,4,7,8,12);
	    Integer result =
	    		digits.stream()
	    		.mapToInt(v -> v)
	    		.max()
	    		.getAsInt();
	System.out.println(result);
}
}	
