package message;

import abstractMessages.AbstractJoinLobbyMessage;
import account.AccountManager;
import model.Player;
import table.TableManager;

public class JoinLobbyMessage extends AbstractJoinLobbyMessage{

	private static final long serialVersionUID = 1L;
	
	public JoinLobbyMessage() {}

	@Override
	public Object execute() {
		
		// Check if player or dealer
		if(AccountManager.getInstance().getAccount(getUsername()).getUser() instanceof Player) {
			TableManager.getInstance().playerJoinsLobby(AccountManager.getInstance().getPlayer(getUsername()));
		} else {
			TableManager.getInstance().dealerJoinsLobby(AccountManager.getInstance().getDealer(getUsername()));
		}
		
		return null;
	}

}
