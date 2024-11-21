package message;

import abstractMessages.AbstractDeposit;

public class DepositMessage extends AbstractDeposit{

	private static final long serialVersionUID = 1L;

	public DepositMessage() {

	}

	public DepositMessage(double amount) {
		setAmount(amount);
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}
}
