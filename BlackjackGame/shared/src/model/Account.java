package model;

import java.io.Serializable;

import observable.Observable;

public class Account extends Observable implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private User user;
	private ROLE role;
	private double balance;

	public Account(String username, String password, ROLE role) {
		if (username == null || username.isEmpty()) {
			System.out.println("Username cannot be blank");
		}
		if (password == null || password.isEmpty()) {
			System.out.println("Password cannot be blank");

		}

		this.username = username;
		this.password = password;

		System.out.println(role);
		if (role.equals(ROLE.DEALER)) {
			this.user = new Dealer();
		} else {
			this.user = new Player(this);
		}

		this.role = role;
		this.balance = 0.0;

	}

	// Getters

	public ROLE getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	// Methods

	public void deposit(double amount) {
		if (amount <= 0) {
			System.out.println("You cannot enter a negative number");
			return;
		}
		balance += amount;
	}

	public void withdraw(double amount) {

		if (amount <= 0) {
			System.out.println("You cannot enter a negative number");
			return;	
		}
		if (amount > balance) {
			System.out.println("Insuficient balance");
			return;
		}
		balance -= amount;
	}

	@Override
	public String toString() {
		return "Account Balance: " + balance; 
	}
}
