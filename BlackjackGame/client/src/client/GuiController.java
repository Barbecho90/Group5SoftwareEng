package client;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sharedModel.LobbyTable;
import sharedModel.Player;
import state.StateManager;

/**
 * Used to dynamically update the gui when we get data from the server
 */
public class GuiController {
	private static GuiController instance = null;
	private DefaultListModel<LobbyTable> lobbyTableListModel;
	private JLabel balance;
	private JPanel userSeatPanel;

	private GuiController() {
		lobbyTableListModel = new DefaultListModel<LobbyTable>();
		balance = new JLabel("ACCOUNT BALANCE:$" + StateManager.getInstance().getAccount().getBalance());
		userSeatPanel = new JPanel();
		userSeatPanel.setLayout(new GridLayout(1, 6, 10, 10));
		updatePlayerSeats(new ArrayList<Player>());
	}

	public static GuiController getInstance() {
		if (instance == null) {
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
		lobbyTableListModel.removeAllElements();

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

	public void updatePlayerSeats(List<Player> players) {
		userSeatPanel.removeAll();
		
		ArrayList<Player> toAdd = players != null ? new ArrayList<Player>(players) : new ArrayList<Player>();
		System.out.println("toAdd: " + toAdd);
		for (int i = 0; i < 6; i++) {
			if (i < toAdd.size()) {
				JLabel userLabel = new JLabel(toAdd.get(i).getAccount().getUsername(), JLabel.CENTER);
				userSeatPanel.add(userLabel);
			} else {
				JLabel userLabel = new JLabel("<EMPTY>", JLabel.CENTER);
				userSeatPanel.add(userLabel);
			}
		}
		userSeatPanel.revalidate();
		userSeatPanel.repaint();
	}

	public JPanel getuserSeatPanel() {
		return userSeatPanel;
	}
}
