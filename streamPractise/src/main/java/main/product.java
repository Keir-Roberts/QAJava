package main;
import java.util.Arrays;
import java.util.List;
public class product {
	public static void main(String[] args) {
	    List<Integer> digits = Arrays.asList(3,4,7,8,12);
	    int result =
	    		digits.stream()
	    		.reduce((a,b) -> a*b)
	    		.get();
	    System.out.println(result);
}
}
