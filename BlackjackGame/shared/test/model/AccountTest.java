package model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.fail;

public class AccountTest {

	@Test
	public void testAccountSuccess() {
		Account account = new Account("testUser", "password", ROLE.DEALER);
		assertEquals("testUser", account.getUsername());
		assertEquals("password", account.getPassword());
		assertEquals(ROLE.DEALER, account.getRole());
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
