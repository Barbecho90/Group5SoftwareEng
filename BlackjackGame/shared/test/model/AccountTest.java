package model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class AccountTest {

	 @Rule
	    public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void testAccountSuccess() {
		Account account = new Account("testUser", "password", ROLE.DEALER);
		assertEquals("testUser", account.getUsername());
		assertEquals("password", account.getPassword());
		assertEquals(ROLE.DEALER, account.getRole());
	}
	
	 @Test
	    public void testAccountCreationFailureWithEmptyUsername() {
	        exceptionRule.expect(IllegalArgumentException.class);  //sets an expectation that the test will throw an IllegalArgumentException
	        exceptionRule.expectMessage("Username cannot be blank");   
	        new Account("", "password", ROLE.DEALER);
	    }

}
