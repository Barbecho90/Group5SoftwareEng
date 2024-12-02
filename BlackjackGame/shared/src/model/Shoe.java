package model;

import java.io.Serializable;

public class Shoe implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Deck[] fourDecks = new Deck[4];
	private Card[] shoe = new Card[208];
	private int shoeIndex = -1;
	private int numCards = 208;

	public Shoe() {
		// Creates 4 decks into a shoe
		int tempShoeIndex = 0;
		for (int i = 0; i < 4; i++) {
			// Create deck
			fourDecks[i] = new Deck();
			fourDecks[i].shuffleDeck();
			for (int j = 0; j < 52; j++) {
				// Iterate over deck. Get card, add to shoe.
				shoe[tempShoeIndex] = fourDecks[i].getCard(j);
				tempShoeIndex++;
			}
		}
	}

	public Card dealNextCard() {
		// Go to top deck, get top card from deck
		shoeIndex++;
		this.numCards--;
		return shoe[shoeIndex];
	}

	public void listAllCards() {
		// Debug method. Just to see all cards in the shoe.
		for (int i = 0; i < shoe.length; i++) {
			System.out.println(shoe[i].getRank() + " " + shoe[i].getSuit());
		}
	}

	public int getNumCards() {
		return this.numCards;
	}
}
