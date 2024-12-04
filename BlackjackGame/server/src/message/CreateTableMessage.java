package message;

import abstractMessages.AbstractCreateTableMessage;
import account.AccountManager;
import sharedModel.Dealer;
import table.TableManager;

public class CreateTableMessage extends AbstractCreateTableMessage{

	private static final long serialVersionUID = 1L;
	
	public CreateTableMessage() {
		
	}

	@Override
	public Object execute() {
		Dealer dealer = AccountManager.getInstance().getDealer(getUsername());
		
		if(dealer == null) {
			return null;
		}
		
		// Returns table id
		return TableManager.getInstance().createTable(dealer);
	}

}
