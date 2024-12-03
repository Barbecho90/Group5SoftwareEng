package message;

import abstractMessages.AbstractBroadcastBalanceMessage;
import client.GuiController;
import state.StateManager;

public class BroadcastBalanceMessage extends AbstractBroadcastBalanceMessage {
	private static final long serialVersionUID = 1L;

	public BroadcastBalanceMessage() {};
	
	@Override
	public Object execute() {
		StateManager.getInstance().getAccount().setBalance(getBlance());
		GuiController.getInstance().getBalance().setText("ACCOUNT BALANCE: $" + getBlance());
		return null;
	}

}
