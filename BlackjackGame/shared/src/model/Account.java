package model;

import enums.ROLE;

public class Account {
	private String username;
	private String password;
	private User user;
	private ROLE role;

	public Account(String username, String password, ROLE role) {
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("Username cannot be blank");
		}
		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("Password cannot be blank");

		}

		this.username = username;
		this.password = password;
		this.role = role;

	}

	// Getters

	public ROLE getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	public User getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
