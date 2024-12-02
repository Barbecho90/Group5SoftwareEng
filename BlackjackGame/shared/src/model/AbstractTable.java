package model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class AbstractTable implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// Dealer class should initialize with a Table
	static private int count = 0;
	private String id;
	private int minBet = 2;
	private int numPlayers = 0;
	protected Shoe shoe;
	protected Dealer hostDealer;
	private int minCardIndBeforeShuffle;
	protected ArrayList<Player> players = new ArrayList<>();
	
	public AbstractTable(Dealer dealer) {

		this.id = "T00" + (count++);
		this.shoe = new Shoe();
		this.hostDealer = dealer;

	}

	public void joinTable(Player player) {
		if (players.size() < 5) {
			players.add(player);
			numPlayers++;
			System.out.println("Player " + player.getId() + " joined the table. Total players: " + numPlayers);

		} else {
			System.out.println("Table is full or closed. Player " + player.getId() + " cannot join.");
		}
	}

	public void leaveTable(Player player) {
		this.players.remove(player);
		this.numPlayers--;
	}

	public Dealer getDealer() {
		return hostDealer;
	}

	/*
	 * getters and setters below
	 */
	public String getId() {
		return this.id;
	}

	public int getMinBet() {
		return this.minBet;
	}

	/*
	 * public void setMinBet(int minBet) { //Dealer will set the minBet once
	 * initialized this.minBet = minBet; }
	 */

	public int getMinCardBeforeShuffle() {
		return this.minCardIndBeforeShuffle;
	}

	/*
	 * public void setMinCardBeforeShuffle(Card minCardBeforeShuffle) { //not sure
	 * what this is this.minCardBeforeShuffle = minCardBeforeShuffle; }
	 */

	public int getNumPlayers() {
		return this.numPlayers;
	}

	public Hand getDealerHand() {
		return null;
	}

	public void setDealerHand(Hand dealerHand) {
		// once dealer deals the cards, they will set the dealerHand
//		this.dealerHand = dealerHand;
	}

	public Card getDealerShowCard() {
		return null;
	}

	public void setDealerShowCard(Card seenCard) {
		// once dealer deals the cards, they will set the seenCard
//		this.dealerShowCard = seenCard;
	}

	public void setMinBet(int minBet) {
		// TODO Auto-generated method stub
		this.minBet = minBet;
	}

	public Shoe getNewShoe() {
		this.shoe = new Shoe();
		return this.shoe;
	}

	public Shoe getShoe() {
		// TODO Auto-generated method stub
		return this.shoe;
	}

	public ArrayList<Player> getPlayerList() {
		return this.players;
	}

	public boolean isOpen() {

		return (hostDealer != null && numPlayers < 5);

	}

	public void setShoe(Shoe shoe) {
		if (shoe == null) {
			throw new IllegalArgumentException("Shoe cannot be null");
		}
		this.shoe = shoe;
	}

	@Override
	public String toString() {
		StringBuilder tableInfo = new StringBuilder();
		tableInfo.append("Table ID: ").append(this.id).append(", Min Bet: ").append(this.minBet)
				.append(", Number of Players: ").append(this.numPlayers); // Assuming getName() exists in Dealer class
		return tableInfo.toString();
	}

}
