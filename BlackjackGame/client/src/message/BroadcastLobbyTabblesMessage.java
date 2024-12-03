package message;

import java.util.List;

import abstractMessages.AbstractBroadcastLobbyTablesMessage;
import client.GuiController;
import model.LobbyTable;

public class BroadcastLobbyTabblesMessage extends AbstractBroadcastLobbyTablesMessage{
	private static final long serialVersionUID = 1L;

	public BroadcastLobbyTabblesMessage() {
	}
	
	public BroadcastLobbyTabblesMessage(List<LobbyTable> tables) {
		setTables(tables);
	}
	
	@Override
	public Object execute() {
		System.out.println("Execute update");
		System.out.println(getTables());
		GuiController.getInstance().updateLobbyTableListModel(getTables());
		
		return null;
	}

}
