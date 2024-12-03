package modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Account;
import model.Player;
import model.ROLE;

import org.junit.Before;


public class PlayerTest{
	private Account account;
	private Player player;
	
	@Before 
	public void setup() {
		account = new Account("user1", "psw", ROLE.PLAYER);
		player = new Player(account);
	}
	
	@Test
	public void TestPlayerConstructor() {
		
		assertEquals(3, player.getId()); //id is set to 3 due to static count
		assertEquals(2, player.getHands().size()); //hand and splitHand added to list of hands
		assertTrue(0.0 == player.getCurrentBet()); //current bet is set
		
	}
	
	@Test 
	public void TestPlaceBet() {
		player.getAccount().setBalance(200);
		player.placeBet(20);
		assertTrue(20 == player.getCurrentBet()); //current bet is set
		assertTrue(180 == player.getAccount().getBalance()); //player balance is update
	}
	
	@Test
	public void TestResetCurrentBet() {
		player.getAccount().setBalance(200);
		player.placeBet(20);
		player.resetCurrentBet();
		assertTrue(0 == player.getCurrentBet()); //current bet is set
	}
	
}