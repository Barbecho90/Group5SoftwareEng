package message;

import abstractMessages.AbstractWithdraw;

public class WithdrawMessage extends AbstractWithdraw {

	private static final long serialVersionUID = 1L;

	public WithdrawMessage() {
		
	}
	
	public WithdrawMessage(double amount) {
		setAmount (amount);
	}
	
	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
