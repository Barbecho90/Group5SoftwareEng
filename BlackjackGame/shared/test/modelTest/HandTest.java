package modelTest;

//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;

//import org.junit.jupiter.api.Test;

import model.Shoe;
import model.Hand;
import model.Card;

public class HandTest {
	
	// Tests constructor, getHand, getHandValue, getBet, and updateHandValue
	@Test
	public void testHandConstructor() {
		int bet = 10;
		Shoe shoe = new Shoe();
		Hand hand = new Hand(shoe.dealNextCard(), shoe.dealNextCard(), bet);
		
		assertEquals(hand.getHand().get(0), shoe.getShoe()[0]);
		assertEquals(hand.getHand().get(1), shoe.getShoe()[1]);
		assertEquals(hand.getHandValue(), shoe.getShoe()[0].getValues()[0] + shoe.getShoe()[1].getValues()[0]);
		assertEquals(hand.getBet(), 10);
	}
	
	@Test
	public void testHandHit() {
		int bet = 10;
		Shoe shoe = new Shoe();
		Hand hand = new Hand(shoe.dealNextCard(), shoe.dealNextCard(), bet);
		
		hand.hit(shoe);
		assertEquals(hand.getHand().get(2), shoe.getShoe()[2]);
	}
	
	// Tests split for hand. And tests value of the new hand
	@Test 
	public void testSetSplit() {
		int bet = 10;
		Shoe shoe = new Shoe();
		Hand hand = new Hand(shoe.dealNextCard(), shoe.dealNextCard(), bet);
		
		Hand secondHand = hand.setSplit();
		assertEquals(hand.getIsSplit(), true);
		assertEquals(hand.getHand().get(0), shoe.getShoe()[0]);
		assertEquals(secondHand.getHand().get(0), shoe.getShoe()[1]);
		assertEquals(hand.getTotalCards() + secondHand.getTotalCards(), 2);
		assertEquals(secondHand.getBet(), bet);
		assertEquals(secondHand.getIsSplit(), true);
	}
	
	@Test
	public void testDoubleDown() {
		int bet = 10;
		Shoe shoe = new Shoe();
		Hand hand = new Hand(shoe.dealNextCard(), shoe.dealNextCard(), bet);
		
		hand.doubleDown(shoe);
		assertEquals(hand.getBet(), bet * 2);
		assertEquals(hand.getHand().get(1), shoe.getShoe()[1]);
	}

}
