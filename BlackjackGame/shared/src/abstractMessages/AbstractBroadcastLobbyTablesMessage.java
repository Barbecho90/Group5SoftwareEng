package abstractMessages;

import java.util.List;

import model.LobbyTable;

public abstract class AbstractBroadcastLobbyTablesMessage extends AbstractMessage{
	private static final long serialVersionUID = 1L;
	
	private List<LobbyTable> tables;

	public List<LobbyTable> getTables() {
		return tables;
	}

	public void setTables(List<LobbyTable> tables) {
		this.tables = tables;
	}
	
	@Override
	public String toString() {
		return tables.toString();
	}
}
