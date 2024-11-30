package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Hand implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Card> hand;
	private int bet;
	private boolean isSplit = false;
	private int handValue = 0;
	private boolean busted;
	private boolean canSplit;
	private boolean canDoubleDown;

	// First ask amount to bet, then we hand one card to all players and then the
	// other hand
//	public Hand(Card card1, Card card2, int bet) {
//		hand = new ArrayList<>();
////		hand.add(card1);
////		hand.add(card2);
////		updateHandValue();
////		this.bet = bet;
//	}

	// Default Constructor
	public Hand() {
		hand = new ArrayList<>();
		this.bet = 0;
		this.isSplit = false;
		// this.handValue =0;
	}
	
	public Hand(Card Card1, Card Card2, int betAmount) {
		hand = new ArrayList<>();
		hand.add(Card1);
		hand.add(Card2);
		this.bet = betAmount;
		this.isSplit = false;
	}

	// Method to add a card to the hand
	public void hit(Shoe shoe) {
		addCard(shoe.dealNextCard());
		updateHandValue();
		calculateActions();
	}

	// Adds a card to the hand
	public void addCard(Card card) {
		hand.add(card);
		updateHandValue();

	}

	// Removes a card from the current hand and creates a new hand object and returns
	// the new hand with the removed card.
	public Hand setSplit() {
		this.isSplit = true;
		Hand splitHand = new Hand();
		splitHand.getHand().add(hand.get(1));
		splitHand.setBet(bet);
		splitHand.isSplit = true;
		hand.remove(1);
		updateHandValue();
		return splitHand;
	}

	public boolean canSplit() {
		return canSplit;
	}

	public boolean canDoubleDown() {
		return canDoubleDown;
	}

	public boolean isBusted() {
		return busted;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}
	
	public int getBet() {
		return this.bet;
	}

	public boolean getIsSplit() {
		return this.isSplit;
	}

	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public int getTotalCards() {
		return hand.size();
	}

	// Calculates the total value of the hand, considering Aces as high or low
	public int getHandValue() {
		int handValue = 0;
		for (Card card : hand) {
			if (card.getRank().equals(Card.CardRank.Ace)) {
				if (handValue + Constants.ACE_HIGH > Constants.MAX_HAND_VALUE) {
					handValue += Constants.ACE_LOW;

				} else {
					handValue += Constants.ACE_HIGH;
				}
			} else {
				handValue += card.getNumericalValue();
			}

		}
		return handValue;
	}

	// Helper Methods
	private void calculateActions() {
		calculateBust();
		calculateDoubleDown();
		calculateCanSplit();
	}

	private void calculateBust() {
		this.busted = getHandValue() > Constants.MAX_HAND_VALUE;
	}

	private void calculateDoubleDown() {
		if (hand.size() != 2) {
			this.canDoubleDown = false;
			return;
		}

		int value = getHandValue();

		canDoubleDown = value == 9 || value == 10 || value == 11;
	}

	public void doubleDown(Shoe shoe) {
		this.bet *= 2;
		this.hit(shoe);
	}

	private void calculateCanSplit() {
		if (hand.size() != 2) {
			this.canSplit = false;
			return;
		}

		Card cardOne = hand.get(0);
		Card cardTwo = hand.get(1);

		canSplit = cardOne.getNumericalValue() == cardTwo.getNumericalValue();
	}

	// Updates the total hand value
	private void updateHandValue() {
		this.handValue = getHandValue();
	}

	public Card getTopCard() {
		// For dealers only. Returns the top card to show to players
		return hand.getFirst();
	}
	// clear hand for new rounds
	public void clear() {
		hand.clear();
		handValue = 0;
		isSplit = false;
		busted = false;
		canSplit = false;
		canDoubleDown = false;
	}
	
	
	

}
