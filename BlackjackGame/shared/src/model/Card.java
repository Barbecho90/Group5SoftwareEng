package model;

import model.Card.CardRank;
import model.Card.CardSuit;

public class Card{
	
	public enum CardSuit{
		Spades,
		Hearts,
		Clubs,
		Diamonds;
	}
	
	public enum CardRank{
		Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
	}

	private CardRank Rank;
	private CardSuit Suit;
	private int[] values = {-1, -1};
	
	public Card(int suitInt, int rankInt) {
		switch(suitInt) {
			case 0: this.Suit = CardSuit.Spades; break;
			case 1: this.Suit = CardSuit.Hearts; break;
			case 2: this.Suit = CardSuit.Clubs; break;
			case 3: this.Suit = CardSuit.Diamonds; break;
			default: this.Suit = CardSuit.Spades; break;
		}
		switch(rankInt) {
			case 1: values[0] = 1; values[1] = 11; this.Rank = CardRank.Ace; break;
			case 2: values[0] = 2; this.Rank = CardRank.Two; break;
			case 3: values[0] = 3; this.Rank = CardRank.Three; break;
			case 4: values[0] = 4; this.Rank = CardRank.Four; break;
			case 5: values[0] = 5; this.Rank = CardRank.Five; break;
			case 6: values[0] = 6; this.Rank = CardRank.Six; break;
			case 7: values[0] = 7; this.Rank = CardRank.Seven; break;
			case 8: values[0] = 8; this.Rank = CardRank.Eight; break;
			case 9: values[0] = 9; this.Rank = CardRank.Nine; break;
			case 10: values[0] = 10; this.Rank = CardRank.Ten; break;
			case 11: values[0] = 10; this.Rank = CardRank.Jack; break;
			case 12: values[0] = 10; this.Rank = CardRank.Queen; break;
			case 13: values[0] = 10; this.Rank = CardRank.King; break;
			default: values[0] = 10; this.Rank = CardRank.King; break;
		}
	}

	public CardSuit getSuit() {
		return Suit;
	}
	
	public CardRank getRank(){
		return Rank;
	}
	
	public int[] getValues() {
		return this.values;
	}

	public int getNumericalValue() {
	        return switch (this.Rank) {
	        case Two -> 2;
	        case Three -> 3;
	        case Four -> 4;
	        case Five -> 5;
	        case Six -> 6;
	        case Seven -> 7;
	        case Eight -> 8;
	        case Nine -> 9;
	        case Ten, Jack, Queen, King -> 10;
	        default -> 1;
        };
	
}
}
