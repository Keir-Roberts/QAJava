package main;

public class car extends vehicles {
private int seatNum;
private boolean automatic;
private int storageSpace;

public int getSeatNum() {
	return seatNum;
}

public void setSeatNum(int seatNum) {
	this.seatNum = seatNum;
}

public boolean isAutomatic() {
	return automatic;
}

public void setAutomatic(boolean automatic) {
	this.automatic = automatic;
}

public int getStorageSpace() {
	return storageSpace;
}

public void setStorageSpace(int storageSpace) {
	this.storageSpace = storageSpace;
}

public car(String manufacturer, String make, String fuelType, int maxSpeed, int seatNum, boolean automatic) {
	super(manufacturer, make, fuelType, maxSpeed);
	this.seatNum = seatNum;
	this.automatic = automatic;
}

}
