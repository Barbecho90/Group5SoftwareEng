package model;

import model.Account;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AccountTest {
	
	@Test
	public void testAccountCreation() {
		Account account = new Account("testUser", "password",ROLE.DEALER);
		assertEquals("testUser", account.getUsername() );
		assertEquals("password", account.getPassword() );
		assertEquals(ROLE.DEALER, account.getRole() );
	}
	
	
	
}
