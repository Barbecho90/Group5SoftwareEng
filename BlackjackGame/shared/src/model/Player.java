package model;

public class Player extends User{
	private static int count =0;
	private int id;
	private Hand playerHand;
	private double currentBet;
	private Account account; //Associate with an account
	
	public Player() {
		
	}
	
	public Player(Account account) {
		if(account == null) {
			throw new IllegalArgumentException("Account can not be null");
			
		}
		this.account = account;
		this.id = count++;
		this.playerHand = new Hand();
		this.currentBet =0;
	}
	
	public void placeBet(double amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("Bet amount  must be positive.");
		}
		if(amount > account.getBalance()) {
			throw new IllegalArgumentException("Insufficient balance to place a Bet. ");
		}
		account.withdraw(amount);
		this.currentBet = amount;
	}
	
		
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void login() {
		// TODO Auto-generated method stub
		
	}
	
	

	
		
}
