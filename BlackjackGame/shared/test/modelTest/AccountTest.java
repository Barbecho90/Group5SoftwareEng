package modelTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import model.Account;
import model.Dealer;
import model.Player;
import model.ROLE;

import static org.junit.Assert.fail;

public class AccountTest {

	@Test
	public void testNewDealer() {
		Account account = new Account("testUser", "password", ROLE.DEALER);
		assertEquals("testUser", account.getUsername());
		assertEquals("password", account.getPassword());
		assertEquals(ROLE.DEALER, account.getRole());
		assertEquals(Dealer.class, account.getUser().getClass());
	}
	
	
	@Test
	public void testNewPlayer() {
		Account account = new Account("player", "password", ROLE.PLAYER);
		assertEquals("player", account.getUsername());
		assertEquals("password", account.getPassword());
		assertEquals(ROLE.PLAYER, account.getRole());
		assertEquals(Player.class, account.getUser().getClass());
	}


	@Test

	public void testAccountCreationFailureWithEmptyUsername() {
		try {
			new Account("", "password", ROLE.DEALER);
			fail("Expected IllegalArgumentException to be thrown"); // fail if exception is not thrown
		} catch (IllegalArgumentException e) {
			assertEquals("Username cannot be blank", e.getMessage()); // check exception message
		}
	}
}
