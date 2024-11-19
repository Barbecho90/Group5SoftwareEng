package message;

import abstractMessages.AbstractWithdraw;

public class WithdrawMessage extends AbstractWithdraw {

	public WithdrawMessage() {

	}

	public WithdrawMessage(double amount) {
		setAmount(amount);
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
