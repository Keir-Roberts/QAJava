package menu;

import controllers.Controller;
import controllers.CustomerController;
import controllers.ItemController;
import controllers.OrdersController;
import dao.CustomerDAO;
import dao.ItemDAO;
import dao.OrdersDAO;
import userInput.logger;
import userInput.userInput;

public class Menu {
	userInput input = userInput.getInstance();
	Controller<?> active;
	CustomerController cusCon;
	ItemController itCon;
	OrdersController ordCon;

	public void logMessage(String input) {
		logger.logMessage(input);
	}

	public void logDebug(String input) {
		logger.logDebug(input);
	}

	public Menu() {
		CustomerDAO cusDAO = new CustomerDAO();
		ItemDAO itDAO = new ItemDAO();
		OrdersDAO orDAO = new OrdersDAO();
		cusCon = new CustomerController(cusDAO);
		itCon = new ItemController(itDAO);
		ordCon = new OrdersController(orDAO);
	}

public void start() {
	boolean recur = true;
	do {
		logMessage("Do you want to focus on: ");
		logMessage("1. Customers");
		logMessage("2. Items");
		logMessage("3. Orders");
		logMessage("4. Exit");
		
		int selection = input.getInt();
		logDebug("Input: " + selection);
		
		switch(selection) {
		case 1: 
			active = cusCon;
			logDebug("active: " + active);
			action();
			break;
		case 2:
			active = itCon;
			logDebug("active: " + active);
			action();
			break;
		case 3:
			active = ordCon;
			logDebug("active: " + active);
			action();
			break;
		case 4:
			recur = false;
		}
	} while (recur);
	}
	public void action() {
		boolean back = false;
		do {
			logMessage("what do you want to do?");
			logMessage("1. Create " + active.singular());
			logMessage("2. Delete " + active.singular());
			logMessage("3. Read " + active.singular());
			logMessage("4. Read All " + active.plural());
			logMessage("5. Update " + active.singular());
			if(active.equals(ordCon)) {
				logMessage("6. Cancel all orders related to a customer or an item.");
			} else {
				logMessage("6. Cancel all orders related to " + active.singular());
			}
				
			System.out.println("7. Return");
			
			int selection = input.getInt();

			switch (selection) {
			case 1:
				active.create();
				break;
			case 2:
				active.delete();
				break;
			case 3:
				active.read();
				break;
			case 4:
				active.readAll();
				break;
			case 5:
				active.update();
				break;
			case 6:
				active.cancelAll();
				break;
			case 7:
				back = true;
			}
		} while (!back);
	}

	}
