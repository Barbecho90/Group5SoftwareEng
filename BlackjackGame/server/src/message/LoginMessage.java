package message;

import abstractMessages.AbstractLogin;
import account.AccountManager;

public class LoginMessage extends AbstractLogin {

	private static final long serialVersionUID = 1L;

	public LoginMessage(){
		
	}
	
	public LoginMessage (String usr, String pwd) {
		username = usr;
		password = pwd;
	}
	
	@Override
	public Object execute() {
		System.out.println("Login "+ username);
		return "success";
		//return AccountManager.getInstance().login(username, password);
	}

}
