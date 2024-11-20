package message;

import abstractMessages.AbstractWithdraw;
import account.AccountManager;

public class WithdrawMessage extends AbstractWithdraw {

	private static final long serialVersionUID = 1L;

	public WithdrawMessage() {

	}

	public WithdrawMessage(double amount) {
		setAmount(amount);
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		System.out.println("User " + getUsername() + " withdrawl " );
		
		return AccountManager.getInstance().withdraw(getUsername(), getAmount());
	}

}
