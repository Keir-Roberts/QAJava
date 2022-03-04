package main;
import java.util.ArrayList;
import java.util.List;
public class personList {
public static List<Object> people = new ArrayList<Object>();
public static void addPeople(Object person) {
	people.add(person);
}
public static Object findPeople(String name) {
	int x = 0;
	for (Object person : people) {
		if(((main.Person) person).getName().equals(name)) {
			break;
		} else { x++;
	} 
} if(x < people.size()) {
	return (Object) people.get(x);
} else {
	return null;
}
}

}
