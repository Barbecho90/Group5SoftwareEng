package message;

import abstractMessages.AbstractLogin;

public class LoginMessage extends AbstractLogin {
	private static final long serialVersionUID = 1L;

	public LoginMessage() {

	}

	public LoginMessage(String usr, String pwd) {
		username = usr;
		password = pwd;
	}

	@Override
	public Object execute() {
		 System.out.println("Executing login for user: " + getUsername());
	        return null;
	}

}
