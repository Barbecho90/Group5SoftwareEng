package modelTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import model.Shoe;
import model.Hand;
import model.Card;

class HandTest {
	int bet = 10;
	Shoe shoe = new Shoe();
	Hand hand = new Hand(shoe.dealNextCard(), shoe.dealNextCard(), bet);
	
	// Tests constructor, getHand, getHandValue, getBet, and updateHandValue
	@Test
	void testHandConstructor() {
		assertEquals(hand.getHand().get(0), shoe.getShoe()[0]);
		assertEquals(hand.getHand().get(1), shoe.getShoe()[1]);
		assertEquals(hand.getHandValue(), shoe.getShoe()[0].getValues()[0]
										+ shoe.getShoe()[1].getValues()[0]);
		assertEquals(hand.getBet(), 10);
	}
	
	@Test
	void testHandHit() {
		hand.hit(shoe);
		assertEquals(hand.getHand().getLast(), shoe.getShoe()[2]);
	}
	
	// Tests split for hand. And tests value of the new hand
	@Test 
	void testSetSplit() {
		Hand secondHand = hand.setSplit();
		assertEquals(hand.getIsSplit(), true);
		assertEquals(hand.getHand().get(0), shoe.getShoe()[0]);
		assertEquals(secondHand.getHand().get(0), shoe.getShoe()[1]);
		assertEquals(hand.getTotalCards() + secondHand.getTotalCards(), 2);
		assertEquals(secondHand.getBet(), bet);
		assertEquals(secondHand.getIsSplit(), true);
	}
	
	@Test
	void testDoubleDown() {
		hand.doubleDown(shoe);
		assertEquals(hand.getBet(), this.bet * 2);
		assertEquals(hand.getHand().get(2), shoe.getShoe()[2]);
	}

}
