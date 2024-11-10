package test;
import model.Account;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import enums.ROLE;

public class accountTest {
	
	@Test
	public void testAccountCreation() {
		Account account = new Account("testUser", "password",ROLE.DEALER);
		assertEquals("testUser", account.getUsername() );
		assertEquals("password", account.getPassword() );
		assertEquals("DEALER", account.getRole() );
	}
	
	
	
}
