package main;


public class Email {
	public String[] specialCharacters = { "`", "¬", "!", "\"", "£", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=",
			"{", "}", "[", "]", ";", ":", "'", "@", "~", "#", "|", "\\", ",", "<", ".", ">", "?", "/" };

	public boolean specialCharValid(String emailIn) {
		for (String ch : specialCharacters) {
			String character = ch;
			if ((emailIn.startsWith(character)) || (emailIn.endsWith(character))) {
				System.out.println("Cannot start or end with a special character.");
				return false;
			}
		}
		return true;
	}

	public boolean DomainAndAddress(String input) {
		String[] DomainAndAddress = input.split("@");
		if ((!(DomainAndAddress.length == 2)) || (DomainAndAddress[1].length() > 10))
			return false;
		else
			return (DomainAndAddress[1].contains(".") && 
					((DomainAndAddress[1].indexOf(".") < (DomainAndAddress[1].length()-1))
							&& (DomainAndAddress[1].indexOf(".") > 0)));
	}

	public boolean isValidEmail(String emailIn) {
		return (DomainAndAddress(emailIn) && (specialCharValid(emailIn)));
	}
}
