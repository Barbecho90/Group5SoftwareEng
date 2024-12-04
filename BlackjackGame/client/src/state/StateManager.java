package state;

import model.ClientConnection;
import model.Table;
import sharedModel.AbstractTable;
import sharedModel.Account;

public class StateManager {
	private static StateManager instance = null;
	
	private ClientConnection client;
	private Account account;
	private AbstractTable table;
	
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

	public AbstractTable getTable() {
		return table;
	}

	public void setTable(AbstractTable abstractTable) {
		this.table = abstractTable;
	}
}
