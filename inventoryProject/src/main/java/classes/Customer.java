package classes;

public class Customer {
	private int ID = 0;
	private String firstName;
	private String lastName;
	private String email;
	private String postcode;
	private String house;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public Customer(String firstName, String lastName, String email, String postcode, String house) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.postcode = postcode;
		this.house = house;
	}
	@Override
	public String toString() {
		return "Customer ID:" + ID + ", Name=" + firstName + " " + lastName;
	}
public String toStringLong() {
	return "Customer ID:" + ID + ", Name=" + firstName + " " + lastName 
			+ ", Email: " + email + ", Postcode: " + postcode + ", House: " + house;
}
}
