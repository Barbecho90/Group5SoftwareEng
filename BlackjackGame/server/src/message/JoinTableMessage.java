package message;

import abstractMessages.AbstractJoinTableMessage;
import account.AccountManager;
import model.Player;
import table.TableManager;

public class JoinTableMessage extends AbstractJoinTableMessage{
	private static final long serialVersionUID = 1L;

	public JoinTableMessage() {
		
	}

	@Override
	public Object execute() {
		Player player = AccountManager.getInstance().getPlayer(getUsername());
		
		if(player == null) {
			return null;
		}
		
		return TableManager.getInstance().joinTable(getTableId(), player);
	}

}
