package message;

import abstractMessages.AbstractGetTablesMessage;
import table.TableManager;

public class GetTablesMessage extends AbstractGetTablesMessage {

	private static final long serialVersionUID = 1L;
	
	public GetTablesMessage() {
		
	}

	@Override
	public Object execute() {
		SimpleMessage message = new SimpleMessage("updateTables");
		message.setTables(TableManager.getInstance().getLobbyTables());
		TableManager.getInstance().getLobby().broadcast(message);
		
		return TableManager.getInstance().getLobbyTables();
	}

}
