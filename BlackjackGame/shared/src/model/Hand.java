package model;

import java.util.ArrayList;

public class Hand{
	private ArrayList<Card> hand;
	private int bet;
	private boolean isSplit = false;
	private int handValue = 0;
	
	public Hand(Card card1, Card card2, int bet) {
		hand = new ArrayList<>();
		hand.add(card1);
		hand.add(card2);
		updateHandValue();
		this.bet = bet;
	}
	
	public Hand(Card card1,int bet, boolean isSplit) {
		hand = new ArrayList<>();
		hand.add(card1);
		updateHandValue();
		this.bet = bet;
		this.isSplit = true;
	}
	
	public void hit(Shoe shoe) {
		addCard(shoe.dealNextCard());
		this.updateHandValue();
	}
	
	// Irrelevant since arraylist already has add function
	public void addCard(Card card) {
		hand.add(card);
	}
	
	// Removes a card from the current hand and creates a new hand and returns
	// the new hand with the removed card.
	public Hand setSplit() {
		this.isSplit = true;
		Hand splitHand = new Hand(this.hand.get(1), this.getBet(), true);
		hand.remove(1);
		return splitHand;
	}
	
	public void doubleDown(Shoe shoe) {
		this.bet *= 2;
		this.hit(shoe);
	}

	public int updateHandValue() {
		handValue = 0;
		int aces = 0;
		for(Card card : hand) {
			handValue += card.getValues()[0];
			// Checks how many aces they are for later use
			if(card.getRank() == Card.CardRank.Ace) {
				aces ++;
				// If it is an ace change it to 11
				handValue + = 10;
			}
		}

		// If there are aces in the hand and the handValue is greater 
		// than 21 then change the ace value to 1.
		while(handValue > 21 && aces > 0) {
			handValue -= 10;
			aces --;
		}
		
		return handValue;
	}
	
	public int getBet() {
		return this.bet;
	}
	
	public boolean getIsSplit() {
		return this.isSplit;
	}
	
	public int getTotalCards() {
		return hand.size();
	}
	
	public int getHandValue() {
		return handValue;
	}
	
	public ArrayList<Card> getHand(){
		return this.hand;
	}
}
