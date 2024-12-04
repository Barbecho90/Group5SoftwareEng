package modelTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import sharedModel.AbstractTable;
import sharedModel.Dealer;
import sharedModel.Lobby;

public class LobbyTest {
	
		
	//test to see if lobby initialize correctly
	@Test
	public void TestLobbyConstructor() {
		assertEquals(0, Lobby.getInstance().getTableList());
		assertEquals(0, Lobby.getInstance().getAvailableDealers());
	}
	
	//test to see if table list is empty on initialization
	@Test
	public void TestTableList() {
		
				
		assertEquals(0, Lobby.getInstance().getTableList().size());
	}
	
	//test to see if adding new tables updates correctly
	@Test
	public void TestNewTableAdded() {
		
		
		
		
		assertEquals(1, Lobby.getInstance().getNumTables());
	}
	
	//test to see if adding new tables updates correctly
		@Test
		public void TestNewDealerAdded() {
			Dealer dealer = new Dealer();
			Lobby.getInstance().newDealer(); //one instance of Lobby
			
			assertEquals(1, Lobby.getInstance().getAvailableDealers());
		}
}
