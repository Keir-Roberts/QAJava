package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import classes.Customer;
import classes.Item;
import classes.orders;
import classes.status;
import dao.CustomerDAO;
import dao.ItemDAO;
import dao.OrdersDAO;
import userInput.logger;
import userInput.userInput;

public class OrdersController implements Controller<orders> {
	private userInput input;
	private OrdersDAO orDAO;
	private CustomerDAO cusDAO;
	private ItemDAO itemDAO;
	private CustomerController cusCon;
	private ItemController itCon;

	public OrdersController(OrdersDAO orDAO) {
		super();
		this.input = userInput.getInstance();
		this.orDAO = orDAO;
		cusDAO = new CustomerDAO();
		itemDAO = new ItemDAO();
		cusCon = new CustomerController(cusDAO);
		itCon = new ItemController(itemDAO);
	}

	public void logMessage(String input) {
		logger.logMessage(input);
	}

	public void logDebug(String input) {
		logger.logDebug(input);
	}

	@Override
	public orders create() {
		int cusID = 0;
		int in = 0;
		while (true) {
			for (Customer i : cusDAO.readAll()) {
				logMessage(i.toString());
			}
			logMessage("Please input the ID of the cutomer making the order, or 0 to cancel");
			cusID = input.getInt();
			logDebug("Input: " + cusID);
			if (cusID == 0)
				return null;
			else if (!cusCon.validID(cusID)) {
				logMessage("Customer not found");
				continue;
			} else {
				break;
			}
		}
		LocalDate OrderDate = LocalDate.now();
		logDebug("Order date: " + OrderDate);
		List<Item> allItems = itemDAO.readAll();
		List<Integer> items = new ArrayList<Integer>();
		while (true) {
			for (Item i : allItems) {
				logMessage(i.toString());
			}
			logMessage("Input the ID of the item you wish to add to the order. Input 0 to stop adding items.");
			in = input.getInt();
			if (in == 0) {
				logDebug("Input: " + in);
				break;
			} else if (!itCon.validID(in)) {
				logDebug("Input: " + in);
				logMessage("Item not found.");
				continue;
			} else {
				items.add(in);
			}
		}
		orders temp = new orders(cusID, OrderDate, items);
		orders out = orDAO.create(temp);
		return out;
	}

	@Override
	public boolean delete() {
		int id = 0;
		readAll();
		boolean recur = true;
		while (recur) {
			logMessage("What is the ID of the order you wish to read?");
			id = input.getInt();
			logDebug("Input: " + id);
			recur = !validID(id);
		}
		if (orDAO.delete(id)) {
			logMessage("Deletion successful");
			return true;
		} else {
			logMessage("Deletion unsuccessful");
			return false;
		}
	}

	@Override
	public List<orders> readAll() {
		if (orDAO.readAll().size() == 0) {
			logMessage("No orders found");
			return null;
		} else {
			List<orders> output = orDAO.readAll();
			for (orders i : output)
				logMessage(i.toString());
			return output;
		}
	}

	@Override
	public orders read() {
		boolean recur = true;
		orders output = null;
		int id = 0;
		readAll();
		while (recur) {
			logMessage("What is the ID of the order you wish to read?");
			id = input.getInt();
			logDebug("Input: " + id);
			recur = !validID(id);
		}
		output = orDAO.read(id);
		logMessage(output.toString());
		return output;
	}

	@Override
	public orders update() {
		int itemId = 0;
		Item item = null;
		int response = 0;
		orders output = null;
		int id = 0;
		readAll();
		while (true) {
			logMessage("What is the ID of the order you wish to read?");
			id = input.getInt();
			logDebug("Input: " + id);
			if (!validID(id)) {
				logMessage("Invalid ID");
				continue;
			} else
				output = orDAO.read(id);
			logDebug("output: " + output.toString());
			break;
		}
		if (output.getStatus() != status.ONGOING) {
			logMessage("Can only update ongoing orders.");
			return output;
		}
		while (true) {
			logMessage(
					"Do you wish to: " + "\n 1. Update the status of this order." + "\n 2. Add an item to this order."
							+ "\n 3. Remove an item from this order." + "\n 0. Exit updating this order.");
			response = input.getInt();
			if (response == 0)
				break;
			switch (response) {
			case 1: {
				logMessage("Do you wish to:" + "\n 1. Cancel this order" + "\n 2. Complete this order"
						+ "\n 0. Go back to the update menu");
				response = input.getInt();
				switch (response) {
				case 0:
					break;
				case 1:
					orDAO.cancel(id);
					break;
				case 2:
					orDAO.complete(id);
					break;

				default:
					break;
				}
				break;
			}
			case 2: {
				for (Item i : itemDAO.readAll()) {
					logMessage(i.toString());
				}
				while (true) {
					logMessage("What is the ID of the item you wish to add?");
					itemId = input.getInt();
					logDebug("Input: " + id);
					if ((id <= 0) || (id > itemDAO.countRow())) {
						logMessage("Invalid ID");
						continue;
					} else
						item = itemDAO.read(id);
					logDebug("item: " + item.toString());
					if (orDAO.checkStock(itemId) >= 1) {
						orDAO.addItem(id, itemId);
					} else {
						logDebug("Stock : " + orDAO.checkStock(itemId));
						logMessage("Not enough stock to add this item");
					}
					break;
				}
			}
			case 3: {
				for (Item i : itemDAO.readAll()) {
					logMessage(i.toString());
				}
				while (true) {
					logMessage("What is the ID of the item you wish to remove?");
					itemId = input.getInt();
					logDebug("Input: " + id);
					if ((id <= 0) || (id > itemDAO.countRow())) {
						logMessage("Invalid ID");
						continue;
					} else
						item = itemDAO.read(id);
					logDebug("item: " + item.toString());
					orDAO.removeItem(id, itemId);
					break;
				}
			}
			}

		}
		return orDAO.read(id);
	}

	public boolean cancelAll() {
		boolean succeed = false;
		while (true) {
			logMessage("Do you want to cancel all Orders for " + "\n1. a specific customer, or " + "\n2. for an item or"
					+ "\n3. return to menu.");
			int inp = input.getInt();
			logDebug("Input: " + inp);
			if (inp == 1) {
				succeed = cusCon.cancelAll();
			} else if (inp == 2) {
				succeed = itCon.cancelAll();
			} else if (inp == 3)
				break;
			else
				logMessage("Please input a valid choice");
		}
		return succeed;
	}

	@Override
	public String singular() {
		return "an order";
	}

	@Override
	public String plural() {
		return "orders";
	}

	@Override
	public boolean validID(int ID) {
		List<orders> Orders = readAll();
		List<Integer> IDs = new ArrayList<Integer>();
		for (orders i : Orders) {
			IDs.add(i.getID());
		}
		return IDs.contains(ID);
	}
}
