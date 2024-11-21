package modelTest;

//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
//import org.junit.jupiter.api.Test;


import model.Card;
import model.Card.CardRank;
import model.Card.CardSuit;

class CardTest {

	@Test
	void testCardConstructor() {
		Card card1 = new Card(3, 3);
		assertEquals(CardSuit.Diamonds, card1.getSuit());
		assertEquals(CardRank.Three, card1.getRank());
		assertEquals(3, card1.getValues()[0]);
	}

}
