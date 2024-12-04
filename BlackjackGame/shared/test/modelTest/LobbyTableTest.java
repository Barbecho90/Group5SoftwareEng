package modelTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import sharedModel.LobbyTable;

public class LobbyTableTest {
	
	LobbyTable table = new LobbyTable("T000", 0, 2);
	
	
	//test constructor, getID(), getNumPlayers(), getMinBet()
	@Test
	public void TestNewTable() {
		
		assertEquals(table.getTableId(), "T000");
		assertEquals(table.getPlayerCount(), 0);
		assertEquals(table.getMinBet(), 2);
		
	}
	
	
	//tests if number of players updates when new player joins, newPlayer(), 
	@Test
	public void TestSetPlayerCount() {
		
		table.setPlayerCount(3);
		assertEquals(table.getPlayerCount(), 3);
		
	}

}
