package main;

public class wallPaint extends roomDecorator{
String wallColour = null;
// takes an inpu for wall colour and does what is shown in roomDecorator's function
	public wallPaint(String colour, Room r) {
		super(r);
		wallColour = colour;
	}
	
	public void describe() {
		super.describe();
		System.out.println(" It has " + wallColour + " walls.");
		// now describe has an extra bit added without having to completely override it
		// also is optional so not all emptyRooms need to have this extra bit.
	}

}
