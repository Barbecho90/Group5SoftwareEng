public class Card{
	
	private enum CardSuit{
		Spades,
		Hearts,
		Clubs,
		Diamonds;
	}
	
	private enum CardRank{
		Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
	}

	private CardRank Rank;
	private CardSuit Suit;
	
	public Card(int suitInt, int rankInt) {
		switch(suitInt) {
			case 0: this.Suit = CardSuit.Spades; break;
			case 1: this.Suit = CardSuit.Hearts; break;
			case 2: this.Suit = CardSuit.Clubs; break;
			case 3: this.Suit = CardSuit.Diamonds; break;
		}
		switch(rankInt) {
			case 0: this.Rank = CardRank.Ace; break;
			case 1: this.Rank = CardRank.Two; break;
			case 2: this.Rank = CardRank.Three; break;
			case 3: this.Rank = CardRank.Four; break;
			case 4: this.Rank = CardRank.Five; break;
			case 5: this.Rank = CardRank.Six; break;
			case 6: this.Rank = CardRank.Seven; break;
			case 7: this.Rank = CardRank.Eight; break;
			case 8: this.Rank = CardRank.Nine; break;
			case 9: this.Rank = CardRank.Ten; break;
			case 10: this.Rank = CardRank.Jack; break;
			case 11: this.Rank = CardRank.Queen; break;
			case 12: this.Rank = CardRank.King; break;
		}
	}

	public Card.CardSuit getSuit() {
		return Suit;
	}
	
	public Card.CardRank getRank(){
		return Rank;
	}
	
}
