package abstractMessages;

import java.util.ArrayList;
import java.util.List;

import model.LobbyTable;

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
	 */
	private String type; // The type of message being sent
	private List<LobbyTable> tables;
	private double balance;
	private double amount;
	
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
}
