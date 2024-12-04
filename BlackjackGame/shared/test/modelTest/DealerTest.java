package modelTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import sharedModel.AbstractTable;
import sharedModel.Card;
import sharedModel.Dealer;
import sharedModel.Lobby;

public class DealerTest {
	
	public Lobby lobby;
	private Dealer dealer;
	private AbstractTable table;
	
	@Before
	public void setup() {
		int minBet = 3;
		lobby = new Lobby();
		dealer = new Dealer(lobby, minBet );
		table = dealer.getTable();
	}
	
	
	//test to see if lobby, table, and dealer are updated on initialization 
	@Test
	public void TestDealerConstructor() {
		
		
		assertEquals(1, lobby.getAvailableDealers()); //dealer count is updated on initialization
		assertEquals(1, lobby.getTableList().size()); //table is added to lobby
		assertEquals(3, table.getMinBet()); //minBet is updated
		assertEquals(0, dealer.getId()); // id is set
		
	}
	
	//test to see if dealing a card will update the shoe in Table
	@Test
	public void TestDealNextCard() {
		
		
		Card card = dealer.getNextCard(); //dealer deals the card
		System.out.println(card.getRank() + " of " + card.getSuit()); //print rank and suit
		assertEquals(207, table.getShoe().getNumCards()); //dealing next card will reduce the numCards--
	}
}
