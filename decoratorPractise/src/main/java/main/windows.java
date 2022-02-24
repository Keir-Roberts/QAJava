package main;

public class windows extends roomDecorator{
int windowAmount = 0;
	public windows(int winNum, Room r) {
		super(r);
		windowAmount = winNum;
	}
public void describe() {
	super.describe();
	System.out.println(" It has " + windowAmount + " windows.");
	// similar to wallPaint, adds extra functionality to describe 
	//without having to alter the class from which we are making our object
}
}
