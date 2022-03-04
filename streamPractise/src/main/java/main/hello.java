package main;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class hello {
	public static void main(String[] args) {
	    List<String> names = Arrays.asList("Michael", "Dean", "James", "Chris");
	    List<String> result =
	    		names.stream()
	    		.filter(str -> !str.equals("James"))
	    		.collect(Collectors.toList());
	    System.out.println(result);
}
}
