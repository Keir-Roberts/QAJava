package main;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class noEven {
	public static void main(String[] args) {
	    List<Integer> digits = Arrays.asList(9, 26, 27, 3,4,7,8,12);
	    List<Integer> result =
	    		digits.stream()
	    		.filter(x -> !(x%2==0))
	    		.collect(Collectors.toList());
	System.out.println(result);
}
}
