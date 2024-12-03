package message;

import abstractMessages.AbstractGetTablesMessage;
import table.TableManager;

public class GetTablesMessage extends AbstractGetTablesMessage {

	private static final long serialVersionUID = 1L;
	
	public GetTablesMessage() {
		
	}

	@Override
	public Object execute() {
		TableManager.getInstance().getLobby().broadcast(new BroadcastLobbyTabblesMessage(TableManager.getInstance().getLobbyTables()));
		return TableManager.getInstance().getLobbyTables();
	}

}
