package client;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;

import model.LobbyTable;
import state.StateManager;

/**
 * Used to dynamically update the gui when we get data from the server
 */
public class GuiController {
	private static GuiController instance = null;
	private DefaultListModel<LobbyTable> lobbyTableListModel;
	private JLabel balance;
	
	private GuiController() {
		lobbyTableListModel = new DefaultListModel<LobbyTable>();
		balance = new JLabel("ACCOUNT BALANCE:$" + StateManager.getInstance().getAccount().getBalance());
	}
	
	public static GuiController getInstance() {
		if(instance == null) {
			instance = new GuiController();
		}
		
		return instance;
	}

	public DefaultListModel<LobbyTable> getLobbyTableListModel() {
		return lobbyTableListModel;
	}

	public void setLobbyTableListModel(DefaultListModel<LobbyTable> lobbyTableListModel) {
		this.lobbyTableListModel = lobbyTableListModel;
	};
	
	public void updateLobbyTableListModel(List<LobbyTable> tables) {
		lobbyTableListModel.clear();
		
		System.out.println(tables);
		
		for (LobbyTable table : tables) {
			lobbyTableListModel.addElement(table);
		}
	}

	public JLabel getBalance() {
		return balance;
	}

	public void setBalance(JLabel balance) {
		this.balance = balance;
	}
}
