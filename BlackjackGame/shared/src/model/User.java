package model;

public class User {
	private Account account;
	static private int count = 0;
	private int id;

	public User(Account account) {
		this.account = account;
		this.id = ++count;
		
		account.setUser(this);// two way relationship

	}

	// Getters
	public Account getAccount() {
		return account;
	}

	public int getId() {
		return id;
	}

}
