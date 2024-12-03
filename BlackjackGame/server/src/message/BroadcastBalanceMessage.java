package message;

import abstractMessages.AbstractBroadcastBalanceMessage;

public class BroadcastBalanceMessage extends AbstractBroadcastBalanceMessage{

	private static final long serialVersionUID = 1L;
	
	public BroadcastBalanceMessage() {};
	public BroadcastBalanceMessage(double balance) {
		setBlance(balance);
	}

	@Override
	public Object execute() {
		// Implementation on the client
		return null;
	}

}
