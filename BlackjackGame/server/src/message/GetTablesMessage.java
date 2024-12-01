package message;

import abstractMessages.AbstractGetTablesMessage;
import table.TableManager;

public class GetTablesMessage extends AbstractGetTablesMessage {

	private static final long serialVersionUID = 1L;
	
	public GetTablesMessage() {
		
	}

	@Override
	public Object execute() {
		return TableManager.getInstance().getLobbyTables();
	}

}
