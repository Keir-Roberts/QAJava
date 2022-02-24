package main;

public class roomDecorator implements Room{
	// this acts as a parent class for any decorators we make
protected Room room;
public roomDecorator(Room r) {
	this.room=r;
	// this makes the roomDecorator() function set the Room object in this class equal to the object we want to create
}
	public void describe() {
		// sets this function to do what it would if we just called our object directly
		this.room.describe();
	}

}
