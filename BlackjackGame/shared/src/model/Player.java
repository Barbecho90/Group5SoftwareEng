package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	private int id;
	private ArrayList<Hand> hands;
	private Hand playerHand;
	private Hand playerSplitHand;
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
		this.hands = new ArrayList<Hand>();
		this.playerHand = new Hand();
		this.playerSplitHand = new Hand();
		this.hands.add(playerHand);
		this.hands.add(playerSplitHand);
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

	public ArrayList<Hand> getHands() {
		return hands;
	}
	
	public void setWinStatus(Boolean bool) {
		this.winStatus = bool;
	}
	
	public Boolean getWinStatus() {
		return this.winStatus;
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
	
	public Hand getHand() {
		return this.playerHand;
	}
	
	public void setHand(Hand hand) {
		this.playerHand = hand;
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
