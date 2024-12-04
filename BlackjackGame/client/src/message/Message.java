package message;

import abstractMessages.AbstractSimpleMessage;
import client.GuiController;
import state.StateManager;

public class Message extends AbstractSimpleMessage{

	private static final long serialVersionUID = 1L;

	public Message() {}
	
	public Message(String type) {
		this.setType(type);
	}
	
	@Override
	public Object execute() {
		switch (getType()) {
		case "updateBalance":
			StateManager.getInstance().getAccount().setBalance(getBalance());
			GuiController.getInstance().getBalance().setText("ACCOUNT BALANCE: $" + getBalance());
			break;
		case "updateTables":
			GuiController.getInstance().updateLobbyTableListModel(getTables());
			break;
		case "joinTable":
			StateManager.getInstance().setTable(getTable());
			GuiController.getInstance().updatePlayerSeats(getPlayers());
			System.out.println("Current players in joinTable");
			System.out.println(getTable().getPlayerList());
			break;
		default:
			break;
		}
		return null;
	}

}
