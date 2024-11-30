package accountTest;

import org.junit.Test;

import account.AccountManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class AccountManagerTest {
	@Test
	public void testLoginSuccess() {
		//
		assertNotEquals(AccountManager.getInstance().login("user1", "user1"), null);
	}
	
	
	@Test
	public void testLoginFailUsername() {
		//
		assertEquals(AccountManager.getInstance().login("user981", "user1"), null);
	}
	
	@Test
	public void testLoginFailPassword() {
		//
		assertEquals(AccountManager.getInstance().login("user1", "wrongPassword"), null);
	}
}
