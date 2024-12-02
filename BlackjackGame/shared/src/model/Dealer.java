package model;

import java.io.Serializable;

public class Dealer extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Card seenCard = null;
	//private Card seenCard;
	//private Card unseenCard;
	private int count = 0;
	private int id;
	private Hand dealerHand;
	private AbstractTable table;
	private Lobby lobby; //get Lobby from server class
	private Card card;
	
	//Default Constructor 
	public Dealer() {
		
	}
	
	public Dealer(Lobby lobby, int minBet) {
		this.lobby = lobby;
		this.lobby.newDealer(); //increase dealer count
		createTable(minBet);
		this.id = count++;
	}
	
	public void createTable(int minBet) {
		//add table to the lobby
		this.lobby.getTableList().add(this.table);
		this.lobby.newTable(); //increase table count
		//set the minBet
		this.table.setMinBet(minBet);
		
	}
	
	public void getNewShoe() {
		// Uses table object to assign it a new shoe.
		table.getNewShoe();
	}
	
	public void dealCards() {
		//deal Cards and update player and dealer hands for the start of the round.
		//set dealerHand for Table using current shoe.
		dealerHand = new Hand(getNextCard(), getNextCard(), 0);
		//set showCard for Table
		this.table.setDealerShowCard(dealerHand.getTopCard());

		// Iterate over current table of players, get player's hand, add the next cards in the shoe into their hand.
		for (int i = 0; i < table.getPlayerList().size(); i++) {
			table.getPlayerList().get(i).getHands().get(0).addCard(getNextCard());
			table.getPlayerList().get(i).getHands().get(0).addCard(getNextCard());
		}
	}

	public Card getNextCard(){
		// Using the table object, call the method which accesses the shoe, which calls for the next card.
		// Used for drawing a single card for another player
		return table.getShoe().dealNextCard();
	}
	
	public void collectMoney() {
		//iterate through all the players at the table and update their balance
		for (int i = 0; i < this.table.getPlayerList().size(); i++) {
			Player player = this.table.getPlayerList().get(i);
			//Double newBalance = player.getAccount().getBalance() - player.getCurrentBet();
			// Iterate over each player's hands
			// If win condition met, update player's balance accoridngly, otherwise, simple remove bet amount from account
			for (int j = 0; j < player.getHands().size(); j++) {
				if (player.getHands().get(j).getHandValue() > dealerHand.getHandValue() &&
					player.getHands().get(j).getHandValue() < 22) {
					double winPrize = player.getAccount().getBalance() + player.getCurrentBet();
					player.getAccount().setBalance(winPrize);
				} else {
					double losePrize = player.getAccount().getBalance() - player.getCurrentBet();
					player.getAccount().setBalance(losePrize);
				}
			}
		}
	}
	
	public void beginGame() {
		
	}
	
	/*
	 * getters and setters below
	 */
	
	/*public void setSeenCard(Card seenCard) {
		this.seenCard = seenCard;
	}*/
	
	public Card getSeenCard() {
		// from dealerHand object, get their card to be used to show to other players.
		seenCard = dealerHand.getTopCard();
		return dealerHand.getTopCard();
	}
	
	/*public void setUnseenCard(Card unseenCard) {
		this.unseenCard = unseenCard;
	}*/
	
	/*public Card getUnseenCard() {
		// Call hand method to return card.
		//return this.dealerHand.blahblahblahfillthisinwhenthefunctionismade;
		// TODO: implement when DealerHand is implemented
				return null;
	}*/
	
	public void setDealerHand(Hand dealerHand) {
		this.dealerHand = dealerHand;
	}
	
	public Hand getDealerHand() {
		return this.dealerHand;
	}

	public AbstractTable getTable() {
		return this.table;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public void login() {
		// TODO Auto-generated method stub
		
	}

}
