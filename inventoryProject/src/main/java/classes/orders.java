package classes;
import java.time.*;
import java.util.List;
public class orders {
private int ID;
private int customerID;
private LocalDate orderDate;
private List<Integer> itemIDs;
private String status;
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public int getCustomerID() {
	return customerID;
}
public void setCustomerID(int customerID) {
	this.customerID = customerID;
}
public LocalDate getOrderDate() {
	return orderDate;
}
public void setOrderDate(LocalDate orderDate) {
	this.orderDate = orderDate;
}
public List<Integer> getItemIDs() {
	return itemIDs;
}
public void setItemIDs(List<Integer> itemIDs) {
	this.itemIDs = itemIDs;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public orders(int iD, int customerID, LocalDate orderDate, List<Integer> itemIDs, String status) {
	super();
	ID = iD;
	this.customerID = customerID;
	this.orderDate = orderDate;
	this.itemIDs = itemIDs;
	this.status = status;
}

}
