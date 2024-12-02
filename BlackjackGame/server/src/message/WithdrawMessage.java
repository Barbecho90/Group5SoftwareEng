package message;

import abstractMessages.AbstractWithdraw;
import account.AccountManager;

public class WithdrawMessage extends AbstractWithdraw {

	private static final long serialVersionUID = 1L;

	//default Constructor
	public WithdrawMessage() {

	}

	//parameterize constructor
	public WithdrawMessage(double amount) {
		setAmount(amount);
	}

	//Executes the deposit operation for the user.
	@Override
	public Object execute() {
		System.out.println("User " + getUsername() + " withdrawl " );
		return AccountManager.getInstance().withdraw(getUsername(), getAmount());
	}

}
