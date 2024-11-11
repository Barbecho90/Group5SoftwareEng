package model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;

public class BalanceTest {
	private Account account;

	@Before
	public void setUp() {
		account = new Account("user1", "user1", ROLE.PLAYER);
	}

	@Test

	public void Validdeposit() {
		account.deposit(100.0);
		assertEquals(100.0, account.getBalance(), 0.001); // margin of 0.001 -> delta is used for minor rounding errors
															// when comparing floating-poin numbers
	}

	@Test
	public void DeposisNegativeAmount() {
		try {
			account.deposit(-100.0);

			fail("Expected IllegalArgumentException for negative deposit");
		} catch (IllegalArgumentException e) {
			assertEquals("Deposits must be positive", e.getMessage());
		}
	}

	@Test
	public void Validwithdraw() {
		account.deposit(200.0);
		account.withdraw(50.0);
		assertEquals(150.0, account.getBalance(), 0.001); // margin of 0.001 -> delta is used for minor rounding errors
															// when comparing floating-poin numbers
	}

	@Test
	public void WithdrawNegativeAmount() {
		account.deposit(100);
		try {

			account.withdraw(-100);
			fail("Expected IllegalArgumentException for negative withdraw");
		} catch (IllegalArgumentException e) {
			assertEquals("Withdrawal must be positive", e.getMessage());
		}
	}

	@Test
	public void WithdrawInsuficcientBalance() {
		account.deposit(100);
		try {

			account.withdraw(200);
			fail("Expected IllegalArgumentException for insufficient balance");
		} catch (IllegalArgumentException e) {
			assertEquals("Insuficient balance", e.getMessage());
		}

	}
}
