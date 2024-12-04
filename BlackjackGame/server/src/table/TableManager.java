package table;

import java.util.ArrayList;
import java.util.List;

import message.SimpleMessage;
import model.Dealer;
import model.Lobby;
import model.LobbyTable;
import model.Player;
import serverModel.Table;
import model.AbstractTable;

public class TableManager {
	private ArrayList<AbstractTable> tables;
	private static TableManager instance = null;

	private TableManager() {
		this.tables = new ArrayList<>();
	}

	public static TableManager getInstance() {
		if (instance == null) {
			instance = new TableManager();
		}
		return instance;
	}

	//Method that return table Id
	public String createTable(Dealer dealer) {
		AbstractTable table = new Table(dealer);
		
		// Remove from watching the lobby and add to watching the table
		Lobby.getInstance().unregister(dealer);
		table.register(dealer);
		
		tables.add(table);
		
		// Update the lobby
		Lobby.getInstance().addNewTable(new LobbyTable(table.getId(), table.getNumPlayers(), table.getMinBet()));
		
		// Update the users in the lobby
		SimpleMessage message = new SimpleMessage("updateTables");
		message.setTables(Lobby.getInstance().getTableList());
		Lobby.getInstance().broadcast(message);
		
		return table.getId();
	}
	
	// Player Joins the lobby
	public void playerJoinsLobby(Player player) {
		Lobby.getInstance().addPlayerToLobby(player);
		
		SimpleMessage message = new SimpleMessage("updateTables");
		message.setTables(Lobby.getInstance().getTableList());
		Lobby.getInstance().broadcast(message);
	}
	
	// Dealer Joins lobby
	public void dealerJoinsLobby(Dealer dealer) {
		Lobby.getInstance().addDealerToLobby(dealer);
		
		SimpleMessage message = new SimpleMessage("updateTables");
		message.setTables(Lobby.getInstance().getTableList());
		Lobby.getInstance().broadcast(message);
	}

	// list of table info
	public String toString() {
		StringBuilder tableList = new StringBuilder("Tables: \n");
		for (AbstractTable table : tables) {
			tableList.append("Table ID: ").append(table.getId()).append("Players: ").append(table.getNumPlayers())
					.append(",Open: ").append(table.isOpen()).append("\n");

		}
		return tableList.toString();
	}
	
	public List<AbstractTable> getTables(){
		return this.tables;
	}
	
	public List<LobbyTable> getLobbyTables() {
		return Lobby.getInstance().getTableList();
	}

	// return all active tables(open and not full)
	public ArrayList<AbstractTable> activeTables() {
		ArrayList<AbstractTable> active = new ArrayList<>();
		for (AbstractTable table : tables) {
			if (table.isOpen()) {
				active.add(table);
			}

		}
		return active;

	}

	// Add a player to a specific table
	public String joinTable(String tableId, Player player) {
		for (AbstractTable table : tables) {
			if (table.getId().equals(tableId)) {
				if (table.isOpen()) {
					// Join the table if able to
					table.joinTable(player); //method from Table class
					
					// Move the player from watching the lobby to watching the game
					Lobby.getInstance().unregister(player);
					table.register(player);
					
					return table.getId();
				} else {
	                System.out.println("Table is closed or full.");
	            }
				break;
			}
		}
		return null; // Table full or not open
	}

	// Remove a player from a specific table
	public boolean leaveTable(String tableId, Player player) {
		for (AbstractTable table : tables) {
			if (table.getId().equals(tableId) && table.getPlayerList().contains(player)) {
				table.leaveTable(player);
				return true;
			}
			break;

		}
		return false; // Table not found
	}
	
	public AbstractTable getTableById(String id) {
		for (AbstractTable table : tables) {
			if (table.getId().equals(id)) {
				return table;
			}
		}
		return null;
	}

	public Lobby getLobby() {
		return Lobby.getInstance();
	}
}
