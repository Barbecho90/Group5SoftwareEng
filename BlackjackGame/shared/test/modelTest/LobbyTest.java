package modelTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import sharedModel.AbstractTable;
import sharedModel.Lobby;

public class LobbyTest {
	
	Lobby lobby = new Lobby();
	
	
	//test to see if lobby initialize correctly
	@Test
	public void TestLobbyConstructor() {
		assertEquals(0, lobby.getNumTables());
		assertEquals(0, lobby.getAvailableDealers());
	}
	
	//test to see if table list is empty on initialization
	@Test
	public void TestTableList() {
		
		ArrayList<AbstractTable> tables = lobby.getTableList();
		int tableSize = tables.size();
		
		assertEquals(0, tableSize);
	}
	
	//test to see if adding new tables updates correctly
	@Test
	public void TestNewTableAdded() {
		lobby.newTable();
		
		assertEquals(1, lobby.getNumTables());
	}
	
	//test to see if adding new tables updates correctly
		@Test
		public void TestNewDealerAdded() {
			lobby.newDealer();
			
			assertEquals(1, lobby.getAvailableDealers());
		}
}
