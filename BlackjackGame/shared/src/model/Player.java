package model;

public class Player extends User {
	private static int count = 0;
	private int id;
	private Hand playerHand;
	private double currentBet;
	private Account account; // Associate with an account
	private Boolean winStatus = false;

	public Player() {

	}

	public Player(Account account) {
		if (account == null) {
			throw new IllegalArgumentException("Account can not be null");

		}
		this.account = account;
		this.id = ++count;
		this.playerHand = new Hand();
		this.currentBet = 0;
	}

	public void placeBet(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Bet amount  must be positive.");
		}
		if (amount > account.getBalance()) {
			throw new IllegalArgumentException("Insufficient balance to place a Bet. ");
		}
		account.withdraw(amount);
		this.currentBet = amount;
	}

	public void setWinStatus(Boolean bool) {
		this.winStatus = bool;
	}
	
	public Boolean getWinStatus() {
		return this.winStatus;
	}
	
	public void setPlayerHand(Hand hand) {
		this.playerHand = hand;
	}
	
	public Hand getPlayerHand() {
		return this.playerHand;
	}
	
	public Account getAccount() {
		return this.account;
	}
	
	public double getCurrentBet() {
		return this.currentBet;
	}

	public int getId() {
		return id;
	}

	@Override
	public void login() {
		// TODO Auto-generated method stub

	}
	 // Method to reset current bet to 0 
    public void resetCurrentBet() {
        this.currentBet = 0;
    }

	@Override
    public String toString() {
        return "Player ID: " + id + ", Current Bet: " + currentBet + ", Account Balance: " + account.getBalance();
    }

}
