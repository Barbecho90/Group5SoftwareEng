package accountTest;

import gameState.GameState;
import model.Account;
import model.Dealer;
import model.Player;
import model.ROLE;
import model.Shoe;
import model.AbstractTable;
import table.TableManager;

public class GameStatTestDriver {

	public static void main(String[] args) {

		GameState gameState;
		AbstractTable table;
		Dealer dealer;
		Player player1, player2;
		Shoe shoe;
		 Account account1, account2, dealerAccount;

	
		dealerAccount = new Account("dealer", "password", ROLE.DEALER);
	
		dealer = (Dealer) dealerAccount.getUser();
		
		
		account1 = new Account("player1", "password", ROLE.PLAYER);
	    account2 = new Account("player2", "password", ROLE.PLAYER);
		
	    
	    player1 = (Player) account1.getUser();
	    player2 =(Player) account2.getUser();
	    
	    player1.getAccount().deposit(1000);
	    player2.getAccount().deposit(1000);
	    
	    
	    System.out.println(" Player Account: \n"+ player1 );
	    System.out.println(" Player Account: \n"+ player2 );
	
	    shoe = new Shoe();
		
		
		String tableId = TableManager.getInstance().createTable(dealer);
		System.out.println("table ID: " + tableId);
		TableManager.getInstance().joinTable(tableId, player1);
		TableManager.getInstance().joinTable(tableId, player2);
		
		table = TableManager.getInstance().getTableById(tableId);  // Assume we want the first active table
		System.out.println(table);
	    
		
	    gameState = new GameState(table);
	    
	    System.out.println("Game state initialized for table: " + table.getId());
        System.out.println("Number of players at the table: " + table.getNumPlayers());
        System.out.println("Is table open? " + table.isOpen());
        gameState.startRound();
        
//        System.out.println("Initial dealer hand: " + gameState.getDealerHand());
//        System.out.println("Initial player1 hand: " + gameState.getCurrentPlayerHand());
//   
	    
	}
}


