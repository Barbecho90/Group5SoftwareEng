package abstractMessages;

import java.util.ArrayList;
import java.util.List;

import sharedModel.AbstractTable;
import sharedModel.LobbyTable;
import sharedModel.Player;

public abstract class AbstractSimpleMessage extends AbstractMessage{

	private static final long serialVersionUID = 1L;
	
	/**
	 * The type of message being sent.  Current types
	 * 
	 * Server:
	 *  - joinLobby
	 *  - deposit
	 *  - withdraw
	 *  
	 *  Client:
	 *   - updateBalance
	 *   - updateTables
	 *   - joinTable
	 */
	private String type; // The type of message being sent
	private List<LobbyTable> tables;
	private double balance;
	private double amount;
	private AbstractTable table;
	private List<Player> players;
	
	// For debugging/logging
	@Override
	public String toString() {
		return "Message type = " + type;
	}
	
	// Getters and setters to add the data and set the types
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<LobbyTable> getTables() {
		return tables;
	}
	public void setTables(List<LobbyTable> tables) {
		this.tables = new ArrayList<LobbyTable>(tables);
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public AbstractTable getTable() {
		return table;
	}
	public void setTable(AbstractTable table) {
		this.table = table;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = new ArrayList<Player>(players);
	}
}
