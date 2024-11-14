package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class UserTest {
	private Account account; 
	private User user;
	
	@Before
	public void setUp() {
		account = new Account ("user1", "user1",ROLE.PLAYER);
		user = new User(account);
	}
	
	@Test
    public void testTwoWayAssociation() {
        // Verify the two-way relationship between User and Account.  Ensures that both arguments refer to the exact same object in memory 
        assertSame(user.getAccount(), account);   
        assertSame(account.getUser(), user);    
    }
	
	@Test
    public void testUserAccountAssociation() {
		//check if two objects are equally valued but not necessarily the same instance.
        assertEquals(account, user.getAccount());   
        assertEquals(user, account.getUser());
    }


}
