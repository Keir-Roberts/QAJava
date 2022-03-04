package main;

import java.util.Arrays;
import java.util.List;

public class minSquareNoEven {
	public static void main(String[] args) {
	    List<Integer> digits = Arrays.asList(9, 26, 27, 3,4,7,8,12);
	    Integer result =
	    		digits.stream()
	    		.map(x -> x*x)
	    		.filter(x -> !(x%2==0))
	    		.mapToInt(v->v)
	    		.min()
	    		.getAsInt();
	System.out.println(result);
}
}
