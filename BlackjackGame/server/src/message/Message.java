package message;

import abstractMessages.AbstractSimpleMessage;
import account.AccountManager;
import model.Player;
import table.TableManager;

public class Message extends AbstractSimpleMessage {

	private static final long serialVersionUID = 1L;

	public Message() {
	};
	
	public Message(String type) {
		this.setType(type);
	}

	@Override
	public Object execute() {
		
		switch (getType()) {
		case "joinLobby":
			if(AccountManager.getInstance().getAccount(getUsername()).getUser() instanceof Player) {
				TableManager.getInstance().playerJoinsLobby(AccountManager.getInstance().getPlayer(getUsername()));
			} else {
				TableManager.getInstance().dealerJoinsLobby(AccountManager.getInstance().getDealer(getUsername()));
			}
			break;
		case "deposit":
			System.out.println("User " + getUsername() + " deposit " );
			return AccountManager.getInstance().deposit(getUsername(), getAmount());
		case "withdraw":
			System.out.println("User " + getUsername() + " withdrawl " );
			return AccountManager.getInstance().withdraw(getUsername(), getAmount());
		default:
			break;
		}
		
		
		return null;
	}

}
