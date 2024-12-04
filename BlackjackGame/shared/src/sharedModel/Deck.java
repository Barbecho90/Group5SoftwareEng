package sharedModel;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Card[] deck = new Card[52];
	
	public Deck() {
		int counterIndex = 0;
		for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 13; j++){
					deck[counterIndex] = new Card(i, j);
					//System.out.println(deck[counterIndex].getRank() + "" + deck[counterIndex].getSuit());
					counterIndex++;
				}
		}
	}
	
	public void shuffleDeck() {
		// Converting the array to a list
        List<Card> cardList = Arrays.asList(deck);

        // Shuffling the list
        Collections.shuffle(cardList);

        // Converting the list back to an array
        deck = cardList.toArray(new Card[0]);

	}
	
	public Card[] getDeck() {
		// Get the array of cards
		return deck;
	}
	
	public Card getCard(int index) {
		// Returns card object from deck. Requires and index to select a card.
		return deck[index];
	}
}
