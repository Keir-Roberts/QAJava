package main;

public class runner {
public static void main(String[] args) {
	// lets make a new room
	Room livingRoom = 
			// we nest the actual class we are instantiating within our decorators
			new windows(2, 
					new wallPaint("red", 
							new emptyRoom("Living Room")
							)
					);
	livingRoom.describe();
	
	Room bedRoom =
			new windows(3, 
					new emptyRoom("Bedroom")
					);
	bedRoom.describe();
	
	Room cupboard =
			new wallPaint("Cream",
					new emptyRoom("cupboard")
					);
	
	cupboard.describe();
}
}
