package modelTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Card;
import model.Dealer;
import model.Lobby;
import model.AbstractTable;

public class DealerTest {
	
	int minBet = 3;
	Lobby lobby = new Lobby();
	Dealer dealer = new Dealer(lobby, minBet );
	AbstractTable table = dealer.getTable();
	
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
