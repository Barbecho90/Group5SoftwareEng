package message;

import abstractMessages.AbstractJoinLobbyMessage;
import state.StateManager;

public class JoinLobbyMessage extends AbstractJoinLobbyMessage{
	private static final long serialVersionUID = 1L;

	public JoinLobbyMessage() {
		setUsername(StateManager.getInstance().getAccount().getUsername());
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}
}
