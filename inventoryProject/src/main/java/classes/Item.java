package classes;

public class Item {
private int ID = 0;
private String productName;
private Float price;
private long stock = 0;
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public Float getPrice() {
	return price;
}
public void setPrice(Float price) {
	this.price = price;
}
public long getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}
public Item(String productName, Float price, long stock) {
	super();
	this.productName = productName;
	this.price = price;
	this.stock = stock;
}
@Override
public String toString() {
	return "Item ID=" + ID  + productName + ", price = £" + price + ", stock = " + stock;
}

}
