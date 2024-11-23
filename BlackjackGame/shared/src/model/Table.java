package model;

import java.util.ArrayList;

public class Table {
	
	//Dealer class should initialize with a Table
	static private int count = 0;
	private int id;
	private int minBet = 2;
	private int numPlayers = 0;
	private Shoe shoe;
	private Dealer hostDealer;
	private int minCardIndBeforeShuffle;
	private boolean isOpen;
	ArrayList<Player> players = new ArrayList<>();
	
	public Table() {
		
		this.id = count++ ;
		this.shoe = new Shoe();
		
	}
	
	public void joinTable(Player player) {
		this.players.add(player);
		this.numPlayers++;
	}
	
	public void leaveTable(Player player) {
		this.players.remove(player);
		this.numPlayers--;
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
		return this.minCardIndBeforeShuffle;
	}

	/*public void setMinCardBeforeShuffle(Card minCardBeforeShuffle) {
		//not sure what this is
		this.minCardBeforeShuffle = minCardBeforeShuffle;
	}*/

	public int getNumPlayers() {
		return this.numPlayers;
	}
	
	public Hand getDealerHand() {
		return null;
	}

	public void setDealerHand(Hand dealerHand) {
		//once dealer deals the cards, they will set the dealerHand
//		this.dealerHand = dealerHand;
	}

	public Card getDealerShowCard() {
		return null;
	}

	public void setDealerShowCard(Card seenCard) {
		//once dealer deals the cards, they will set the seenCard
//		this.dealerShowCard = seenCard;
	}

	public void setMinBet(int minBet) {
		// TODO Auto-generated method stub
		this.minBet=minBet;
	}
	
	public Shoe getNewShoe() {
		this.shoe = new Shoe();
		return this.shoe;
	}
	public Shoe getShoe() {
		// TODO Auto-generated method stub
		return this.shoe;
	}

	public ArrayList<Player> getPlayerList(){
		return this.players;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	
	
	
	
}
