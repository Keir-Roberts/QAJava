package controllers;

import userInput.logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import classes.Customer;
import dao.CustomerDAO;
import userInput.userInput;

public class CustomerController implements Controller<Customer> {
	private userInput input;
	private CustomerDAO cusDAO;
	private Pattern emailVer = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b");
	private Pattern nameVer = Pattern.compile("[^A-Za-z-]");
	private Pattern postcodeVer = Pattern.compile("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$");

	public void logMessage(String input) {
		logger.logMessage(input);
	}

	public void logDebug(String input) {
		logger.logDebug(input);
	}

	public boolean NameVerify(String Name) {
		Matcher NameMatch = nameVer.matcher(Name);
		return !NameMatch.find();
	}

	public boolean emailVerify(String email) {
		Matcher emailMatch = emailVer.matcher(email);
		return !emailMatch.find();
	}

	public boolean postcodeVerify(String postcode) {
		Matcher postcodeMatch = postcodeVer.matcher(postcode);
		return !postcodeMatch.find();
	}

	public CustomerController(CustomerDAO cusDAO) {
		super();
		this.input = userInput.getInstance();
		this.cusDAO = cusDAO;
	}

	@Override
	public Customer create() {
		String firstName = null;
		String lastName = null;
		String email = null;
		String postcode = null;
		boolean recur = false;
		while (!recur) {
			logMessage("What is your first name?");
			firstName = input.getString();
			logDebug("Input: " + firstName);
			recur = NameVerify(firstName);
			if (!recur)
				logMessage("Please don't input special characters.");
		}
		recur = false;
		while (!recur) {
			logMessage("What is your last name?");
			lastName = input.getString();
			logDebug("Input: " + lastName);
			recur = NameVerify(lastName);
			if (!recur)
				logMessage("Please don't input special characters.");
		}
		recur = false;
		while (!recur) {
			logMessage("What is your email?");
			email = input.getString();
			logDebug("Input: " + email);
			recur = emailVerify(email);
			if (!recur)
				logMessage("Please input a valid email.");
		}
		recur = false;
		while (!recur) {
			logMessage("What is your postcode");
			postcode = input.getString();
			logDebug("Input: " + postcode);
			recur = postcodeVerify(postcode);
			if (!recur)
				logMessage("Please input a valid postcode.");
		}
		logMessage("What is your house name / number?");
		String house = input.getString();
		logDebug("Input: " + house);
		Customer temp = new Customer(firstName, lastName, email, postcode, house);
		cusDAO.create(temp);
		logDebug(temp.toString());
		return temp;

	}

	@Override
	public Customer read() {
		boolean recur = true;
		Customer output = null;
		int id = 0;
		readAll();
		while (recur) {
			logMessage("What is the ID of the customer you wish to read?");
			id = input.getInt();
			logDebug("Input: " + id);
			recur = !validID(id);
			output = cusDAO.read(id);
			logDebug("output: " + output);
			logMessage(output.toStringLong());
		}
		return output;
	}
	
	@Override
	public List<Customer> readAll() {
		List<Customer> output = cusDAO.readAll();
		for (Customer i : output)
			logMessage(i.toString());
		return output;
	}
	
	@Override
	public Customer update() {
		String firstName = null;
		String lastName = null;
		String email = null;
		String postcode = null;
		boolean recur = true;
		int id = 0;
		readAll();
		while (recur) {
			logMessage("What is the ID of the customer you wish to update?");
			id = input.getInt();
			logDebug("Input: " + id);
			recur = !validID(id);
		}
		recur = true;
		while (recur) {
			logMessage("What is your first name?");
			firstName = input.getString();
			logDebug("Input: " + firstName);
			recur = NameVerify(firstName);
			if (!recur) {
				logMessage("Please don't input special characters.");
			} else recur = false;
		}
		recur = true;
		while (recur) {
			logMessage("What is your last name?");
			lastName = input.getString();
			logDebug("Input: " + lastName);
			recur = NameVerify(lastName);
			if (!recur) {
				logMessage("Please don't input special characters.");
			} else recur = false;
		}
		recur = true;
		while (recur) {
			logMessage("What is your email?");
			email = input.getString();
			logDebug("Input: " + email);
			recur = emailVerify(email);
			if (!recur) {
				logMessage("Please input a valid email.");
			} else recur = false;
		}
		recur = true;
		while (recur) {
			logMessage("What is your postcode");
			postcode = input.getString();
			logDebug("Input: " + postcode);
			recur = postcodeVerify(postcode);
			if (!recur) {
				logMessage("Please input a valid postcode.");
			} else recur = false;
		}
		logMessage("What is your house name / number?");
		String house = input.getString();
		logDebug("Input: " + house);
		Customer temp = cusDAO.update(id, firstName, lastName, email, postcode, house);
		logDebug(temp.toStringLong());
		return temp;
	}
	
	@Override
	public boolean delete() {
		
		readAll();
		boolean recur = true;
		int id = 0;
		while (recur) {
			logMessage("What is the ID of the customer you wish to delete?");
			id = input.getInt();
			logDebug("Input: " + id);
			recur = !validID(id);
		}
		List<Integer> ongoing = cusDAO.getOngoing(id);
		if (ongoing.size() > 0) {
			logMessage("Customer ID: " + id + " has ongoing orders with orderID(s) of: " + ongoing);
			while(recur) {
			logMessage("Do you want to:"
					+ "\n 1. Change status of all of this customer's orders to CANCELLED before deleting this customer."
					+ "\n 2. Cancel the deletion of this customer.");
			int reply = input.getInt();
			if (reply == 1) {
				cusDAO.cancelAll(id);
				if (cusDAO.delete(id)) {
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
		else if (cusDAO.delete(id)) {
			logMessage("Delete successful");
			return true;
		} else  {
			logMessage("Delete unsuccessful");
			return false;			
		}
		return false;
	}

	public boolean cancelAll() {
		boolean recur = true;
		List<Integer> output = null;
		int id = 0;
		readAll();
		while (recur) {
			logMessage("What is the ID of the customer you wish to cancel all orders for? Input 0 to cancel.");
			id = input.getInt();
			logDebug("Input: " + id);
			if (id == 0) {
				return false;
			}
			else if (!validID(id)) {
				logMessage("Invalid ID");
				continue;
			} else
				recur = false;
		} 
		output = cusDAO.cancelAll(id);
		if (output.size() == 0) {
			logMessage("No orders were cancelled");
			return false;
		} else
			return true;
	}

	@Override
	public String singular() {
		return "a customer";
	}

	@Override
	public String plural() {
		return "customers";
	}

	@Override
	public boolean validID(int ID) {
		List<Customer> customers = readAll();
		List<Integer> IDs = new ArrayList<Integer>();
		for(Customer i: customers) {
			IDs.add(i.getID());
		}
		return IDs.contains(ID);
	}
	

	

	

}
