package model;

public class Table {
	
	//Dealer class should initialize with a Table
	static private int count = 0;
	private int id;
	private int minBet = 2;
	private int numPlayers = 0;
	private Shoe shoe = new Shoe();
	private Dealer hostDealer;
	private int minCardIndBeforeShuffle;
	
	public Table() {
		
		this.id = count++ ;
		
	}
	
	public void newPlayer() {
		//player will call newPlayer() when they join a table to update the count
		this.numPlayers++;
	}
	
	
	/*
	 * getters and setters below
	 */
	public int getId() {
		return this.id;
	}

	public int getMinBet() {
		return this.minBet;
	}

	/*public void setMinBet(int minBet) {
		//Dealer will set the minBet once initialized
		this.minBet = minBet;
	}*/

	public int getMinCardBeforeShuffle() {
		return this.minCardBeforeShuffle;
	}

	/*public void setMinCardBeforeShuffle(Card minCardBeforeShuffle) {
		//not sure what this is
		this.minCardBeforeShuffle = minCardBeforeShuffle;
	}*/

	public int getNumPlayers() {
		return this.numPlayers;
	}
	
	public Hand getDealerHand() {
		return this.dealerHand;
	}

	public void setDealerHand(Hand dealerHand) {
		//once dealer deals the cards, they will set the dealerHand
		this.dealerHand = dealerHand;
	}

	public Card getDealerShowCard() {
		return dealerShowCard;
	}

	public void setDealerShowCard(Card seenCard) {
		//once dealer deals the cards, they will set the seenCard
		this.dealerShowCard = seenCard;
	}
	
	
	
	
	
}
