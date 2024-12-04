package state;

import clientModel.ClientConnection;
import clientModel.Table;
import model.AbstractTable;
import model.Account;

public class StateManager {
	private static StateManager instance = null;
	
	private ClientConnection client;
	private Account account;
	private Table table;
	
	private StateManager() {
		client = new ClientConnection();
	}
	
	public static StateManager getInstance() {
		if (instance == null) {
			instance = new StateManager();
		}
		
		return instance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account= account;
	}
	
	public ClientConnection getClient() {
		return client;
	}

	public void setClient(ClientConnection client) {
		this.client = client;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
}
