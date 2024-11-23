package model;

public class Dealer extends User {
	private static final Card seenCard = null;
	//private Card seenCard;
	//private Card unseenCard;
	private int count = 0;
	private int id;
	private Hand dealerHand;
	private Table table = new Table();
	private Lobby lobby; //get Lobby from server class
	private Card card;
	
	//Default Constructor 
	public Dealer() {
		
	}
	
	public Dealer(Lobby lobby, int minBet) {
		this.lobby  = lobby;
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
		//deal Cards and update player and dealer hands
		//set dealerHand for Table
		this.table.setDealerHand(this.dealerHand);
		//set showCard for Table
		this.table.setDealerShowCard(this.seenCard);

		// This will be a facade, calling getNextCard() and then sending that card to certain player.
	}

	public Card getNextCard(){
		// Using the table object, call the method which accesses the shoe, which calls for the next card.
		return table.getShoe().dealNextCard();
	}
	
	public void collectMoney() {
		//iterate through all the players at the table and update their balance if they have lost
		for (int i = 0; i < this.table.getPlayerList().size(); i++) {
			Player player = this.table.getPlayerList().get(i);
			Double newBalance = player.getAccount().getBalance() - player.getCurrentBet();
			if(player.getWinStatus() == false) {
				player.getAccount().setBalance(newBalance);
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
		return dealerHand.getTopCard();
	}
	
	/*public void setUnseenCard(Card unseenCard) {
		this.unseenCard = unseenCard;
	}*/
	
	public Card getUnseenCard() {
		// Call hand method to return card.
		//return this.dealerHand.blahblahblahfillthisinwhenthefunctionismade;
		// TODO: implement when DealerHand is implemented
				return null;
	}
	
	public void setDealerHand(Hand dealerHand) {
		this.dealerHand = dealerHand;
	}
	
	public Hand getDealerHand() {
		return this.dealerHand;
	}

	public Table getTable() {
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
