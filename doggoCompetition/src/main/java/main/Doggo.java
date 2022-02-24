package main;
import java.util.List;
import java.util.ArrayList;
public class Doggo {
public static List<String> dogCheck(int place) {
	List<String> dogs = new ArrayList<String>();
	if ((place > 100) || (place < 1)) {
		dogs.add("Invalid Input");
		return dogs;
	}
	for (int i=1; i<=100; i++) {
		if((i>20) || (i<11)) {
			if (i%10 == 1) {
				dogs.add(i + "st");
			} else if (i%10 == 2) {
				dogs.add(i + "nd");
			} else if (i%10 == 3) {
				dogs.add(i + "rd");
			} else {
				dogs.add(i + "th");
			}
		} else {
			dogs.add(i + "th");
		}
	}
	dogs.remove(place - 1);
	
	return dogs;
}
}
