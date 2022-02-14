package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ArrayLists {
	public static 	List<String> books = new ArrayList<>();

public static void main(String[] args) {
books.add("Midnight Radio");
books.add("Howl's Moving Castle");
books.add("The Long Way to a Small Angry Planet");
books.add("A closed but common orbit");
books.add("Crooked Kingdom");
Collections.sort(books);
System.out.println(books);
for(String str: books) {
	System.out.println(str);
}
books.remove(0);
Collections.reverse(books);
Collections.swap(books, 2, 3);
for(int i = 0; i < books.size(); i++) {
	System.out.println(books.get(i));
}
System.out.println(books.get(1));
}
}
