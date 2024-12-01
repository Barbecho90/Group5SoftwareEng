package message;

import abstractMessages.AbstractCreateTableMessage;
import state.StateManager;

public class CreateTableMessage extends AbstractCreateTableMessage{
	private static final long serialVersionUID = 1L;
	
	public CreateTableMessage() {
		this.setUsername(StateManager.getInstance().getAccount().getUsername());
	}

	@Override
	public Object execute() {
		// No Execute on client side
		return null;
	}

}
