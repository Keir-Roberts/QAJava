package controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import classes.Item;
import dao.ItemDAO;
import userInput.logger;
import userInput.userInput;

public class ItemController implements Controller<Item> {
	private userInput input;
	private ItemDAO itemDAO;
	
	@Override
public boolean validID(int ID) {
	List<Item> items = readAll();
	List<Integer> IDs = new ArrayList<Integer>();
	for(Item i: items) {
		IDs.add(i.getID());
	}
	return IDs.contains(ID);
}
	
	public void logMessage(String input) {
		logger.logMessage(input);
	}

	public void logDebug(String input) {
		logger.logDebug(input);
	}

	public ItemController(ItemDAO itemDAO) {
		super();
		this.input = userInput.getInstance();
		this.itemDAO = itemDAO;
	}

	@Override
	public Item create() {
		String productName = null;
		String price = null;
		long stock = 0;
		logMessage("What is the product name?");
		productName = input.getString();
		logDebug("Input: " + productName);
		logMessage("What is its price?");
		double priceIn = Double.parseDouble(input.getString());
		DecimalFormat df = new DecimalFormat("0.00");
		logDebug("Price in: " + priceIn);
		price = df.format(priceIn);
		logDebug("Input: " + priceIn);
		logDebug("price: " + price);
		logMessage("What is its stock?");
		String stockIn = input.getString();
		stock = Long.parseLong(stockIn);
		logDebug("Input: " + stockIn);
		logDebug("stock: " + stock);
		Item temp = new Item(productName, price, stock);
		itemDAO.create(temp);
		return temp;
	}

	@Override
	public boolean delete() {
		readAll();
		boolean recur = true;
		int id = 0;
		while (recur) {
			logMessage("What is the ID of the item you wish to delete?");
			id = input.getInt();
			logDebug("Input: " + id);
			recur = !validID(id);
		}
		List<Integer> ongoing = itemDAO.getOngoing(id);
		if (ongoing.size() > 0) {
			recur = true;
			logMessage("Customer ID: " + id + " has ongoing orders with orderID(s) of: " + ongoing);
			while(recur) {
			logMessage("Do you want to:"
					+ "\n 1. Change status of all of this customer's orders to CANCELLED before deleting this customer."
					+ "\n 2. Cancel the deletion of this customer.");
			int reply = input.getInt();
			if (reply == 1) {
				itemDAO.cancelAll(id);
				if (itemDAO.delete(id)) {
					logMessage("Delete successful");
					return true;
				} else  {
					logMessage("Delete unsuccessful");
					return false;			
				}
				
			} else if (reply == 2) {
				return false;
			} else {
				logMessage("Unexpected response, please input 1 or 2");
				continue;
			}
			}
		}
		else if (itemDAO.delete(id)) {
			logMessage("Delete successful");
			return true;
		} else  {
			logMessage("Delete unsuccessful");
			return false;			
		}
		return false;
	}
	@Override
	public List<Item> readAll() {
		List<Item> output = itemDAO.readAll();
		for (Item i : output)
			logMessage(i.toString());
		return output;
	}

	@Override
	public Item read() {
		boolean recur = true;
		Item output = null;
		int id = 0;
		while (recur) {
			logMessage("What is the ID of the item you wish to read?");
			id = input.getInt();
			logDebug("Input: " + id);
			recur = !validID(id);
			output = itemDAO.read(id);
			logMessage(output.toString());
		}
		return itemDAO.read(id);
	}
	public boolean cancelAll() {
		boolean recur = true;
		List<Integer> output = null;
		int id = 0;
		readAll();
		while (recur) {
			logMessage("What is the ID of the item you wish to cancel all orders for? Input 0 to cancel.");
			id = input.getInt();
			logDebug("Input: " + id);
			if (id == 0) {
				return false;
			}
			else if (recur = !validID(id));
		} 
		output = itemDAO.cancelAll(id);
		if (output.size() == 0) {
			logMessage("No orders were cancelled");
			return false;
		} else
			return true;
	}
	
	@Override
	public Item update() {
		String productName = null;
		double price = 0;
		long stock = 0;
		boolean recur = true;
		int id = 0;
		while (recur) {
			logMessage("What is the ID of the item you wish to delete?");
			id = input.getInt();
			logDebug("Input: " + id);
			recur = !validID(id);
		}
		logMessage("What is the updated product name?");
		productName = input.getString();
		logDebug("Input: " + productName);
		logMessage("What is its updated price?");
		String priceIn = input.getString();
		price = Math.round(Double.parseDouble(priceIn) * 100) / 100;
		logDebug("Input: " + priceIn);
		logDebug("price: " + price);
		logMessage("What is its updated stock?");
		String stockIn = input.getString();
		stock = Long.parseLong(stockIn);
		logDebug("Input: " + stockIn);
		logDebug("stock: " + stock);
		return itemDAO.update(id, productName, price, stock);
	}

	@Override
	public String singular() {
		// TODO Auto-generated method stub
		return "an item";
	}

	@Override
	public String plural() {
		return "items";
	}

}
