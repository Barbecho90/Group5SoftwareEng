package message;

import abstractMessages.AbstractDeposit;
import state.StateManager;

public class DepositMessage extends AbstractDeposit{

	private static final long serialVersionUID = 1L;

	public DepositMessage() {
		this.setUsername(StateManager.getInstance().getAccount().getUsername());
	}

	public DepositMessage(double amount) {
		setAmount(amount);
		this.setUsername(StateManager.getInstance().getAccount().getUsername());
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}
}
