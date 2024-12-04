package Driver;

import sharedModel.Account;
import sharedModel.Player;
import sharedModel.ROLE;

public class Driver {
	public static void main(String[] args) {

		// create an account 
		Account account = new Account("User1", "User1", ROLE.PLAYER);
		System.out.println("Initial Account Info" + account);

		// Deposit money into the account
		account.deposit(100.0);
		System.out.println("After Deposit: " + account);

		// Retrieve the Player object associated with the Account
		Player player = (Player) account.getUser(); // Casting the User to Player

		System.out.println("Player Info: " + player);
		player.placeBet(50.0);
		System.out.println("Player Info after beating : " + player);

		//Reset the bet 
		player.resetCurrentBet();
		
		// Withdraw money from the account
		account.withdraw(30.0);
		System.out.println("After Withdrawal: " + player);

		//create an account for dealer
		
		
		
	}

}
