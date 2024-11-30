package message;

import abstractMessages.AbstractWithdraw;
import state.StateManager;

public class WithdrawMessage extends AbstractWithdraw {

	private static final long serialVersionUID = 1L;

	public WithdrawMessage() {
		this.setUsername(StateManager.getInstance().getAccount().getUsername());
	}
	
	public WithdrawMessage(double amount) {
		setAmount (amount);
		this.setUsername(StateManager.getInstance().getAccount().getUsername());
	}
	
	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
