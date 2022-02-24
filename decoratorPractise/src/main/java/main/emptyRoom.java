package main;

public class emptyRoom implements Room{
	// this is the actual class for objects we will create
	public String roomName;

	public void describe() {
		System.out.println("This is a room called the " + roomName + ".");
	}

	public emptyRoom(String roomName) {
		super();
		this.roomName = roomName;
	}
}