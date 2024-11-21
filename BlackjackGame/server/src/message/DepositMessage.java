package message;

import abstractMessages.AbstractDeposit;
import account.AccountManager;

public class DepositMessage extends AbstractDeposit {

	private static final long serialVersionUID = 1L;

	public DepositMessage() {

	}

	public DepositMessage(double amount) {
		setAmount(amount);
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		System.out.println("User " + getUsername() + " deposit " );
		return AccountManager.getInstance().deposit(getUsername(), getAmount());
	}

}
