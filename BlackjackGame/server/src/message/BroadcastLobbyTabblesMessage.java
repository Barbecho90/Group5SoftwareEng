package message;

import java.util.List;

import abstractMessages.AbstractBroadcastLobbyTablesMessage;
import model.LobbyTable;

public class BroadcastLobbyTabblesMessage extends AbstractBroadcastLobbyTablesMessage{
	private static final long serialVersionUID = 1L;

	public BroadcastLobbyTabblesMessage() {}
	
	public BroadcastLobbyTabblesMessage(List<LobbyTable> tables) {
		System.out.println("broadcast constructor");
		System.out.println(tables);
		
		setTables(tables);
	}
	
	@Override
	public Object execute() {
		// Broadcasts for clients to execute
		return null;
	}
	
}
