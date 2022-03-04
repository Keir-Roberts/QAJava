package userInput;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class logger {
	public static Logger LOGGER = LogManager.getLogger();
	public static void logMessage(String message) {
		LOGGER.info(message);
	}
	public static void logDebug(String message) {
		LOGGER.debug(message);
	}
}
