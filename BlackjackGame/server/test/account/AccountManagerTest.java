package account;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountManagerTest {
	@Test
	public void testLoginSuccess() {
		//
		assertTrue(AccountManager.getInstance().login("user1", "user1"));
	}
	
	
	@Test
	public void testLoginFailUsername() {
		//
		assertFalse(AccountManager.getInstance().login("user981", "user1"));
	}
	
	@Test
	public void testLoginFailPassword() {
		//
		assertFalse(AccountManager.getInstance().login("user1", "wrongPassword"));
	}
}
