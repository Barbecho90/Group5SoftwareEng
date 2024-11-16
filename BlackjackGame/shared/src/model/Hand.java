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
	
	public void hit(Shoe shoe) {
		addCard(shoe.dealNextCard());
		this.updateHandValue();
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	// Removes a card from the current hand and creates a new hand and returns
	// the new hand with the removed card.
	public ArrayList<Card> setSplit() {
		this.isSplit = true;
		ArrayList<Card> splitHand = new ArrayList<Card>();
		splitHand.add(hand.get(1));
		hand.remove(1);
		return splitHand;
	}
	
	public void doubleDown(Shoe shoe) {
		this.bet *= 2;
		this.hit(shoe);
	}
	
	public int updateHandValue() {
		handValue = 0;
		for(Card card : hand) {
			handValue += card.getValues()[0];
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
}
