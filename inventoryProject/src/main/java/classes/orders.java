package classes;

import java.time.*;
import java.util.List;

public class orders {
	private int ID = 0;
	private int customerID;
	private LocalDate orderDate;
	private List<Integer> itemIDs;
	private status status = classes.status.ONGOING;

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

	public classes.status getStatus() {
		return status;
	}

	public void setStatus(classes.status status) {
		this.status = status;
	}

	public orders(int customerID, LocalDate orderDate, List<Integer> itemIDs) {
		super();
		this.customerID = customerID;
		this.orderDate = orderDate;
		this.itemIDs = itemIDs;

	}

	@Override
	public String toString() {
		return "OrderID =" + ID 
				+ "\n Customer ID =" + customerID 
				+ "\n Order Date =" + orderDate 
				+ "\n Items in basket, IDs =" + itemIDs 
				+ "\n status =" + status;
	}

}
