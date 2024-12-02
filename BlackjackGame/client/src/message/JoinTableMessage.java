package message;

import abstractMessages.AbstractJoinTableMessage;
import state.StateManager;

public class JoinTableMessage extends AbstractJoinTableMessage{

	private static final long serialVersionUID = 1L;

	public JoinTableMessage() {
		this.setUsername(StateManager.getInstance().getAccount().getUsername());
	}
	
	public JoinTableMessage(String tableId) {
		this.setUsername(StateManager.getInstance().getAccount().getUsername());
		this.setTableId(tableId);
	}
	
	@Override
	public Object execute() {
		// No Execute on client side
		return null;
	}

}
