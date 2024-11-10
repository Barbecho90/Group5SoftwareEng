package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import enums.ROLE;

public class AccountManager {
	private Map<String, Account> accounts = new HashMap<>();

	private static final String FILE_PATH = "user_credentials.txt"; // File to store accounts
	private static AccountManager instance = null;

	private AccountManager() {
		this.accounts = new HashMap<String, Account>();
	}

	public static AccountManager getInstance() {
		if (instance == null) {
			return new AccountManager();
		}

		return instance;
	}

	public void loadAccounts() {
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				String[] parts = line.split(",");
				if (parts.length == 3) {
					String username = parts[0];
					String password = parts[1];
					ROLE role;
					try {
						role = ROLE.valueOf(parts[2].toUpperCase()); // Convert to uppercase to match enum constants
					} catch (IllegalArgumentException e) {
						System.err.println("Invalid role for user " + username + ": " + parts[2]);
						continue; // Skip this account if the role is invalid
					}
					accounts.put(username, new Account(username, password, role));
				}
			}
		} catch (IOException e) {
			System.err.println("Error loading accounts: " + e.getMessage());
		}
	}

	public boolean login(String username, String password) {
		Account account = accounts.get(username);
		return account != null && account.getPassword().equals(password); // Check credentials
	}

}