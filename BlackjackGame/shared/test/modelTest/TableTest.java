package modelTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import model.AbstractTable;

public class TableTest {
	
	AbstractTable table = new AbstractTable();
	
	
	//test construtor, getID(), getNumPlayers(), getMinBet()
	@Test
	public void TestNewTable() {
		
		assertEquals(table.getId(), 0);
		assertEquals(table.getNumPlayers(), 0);
		assertEquals(table.getMinBet(), 2);
		
	}
	
	
	//tests if number of players updates when new player joins, newPlayer(), 
	@Test
	public void TestNewPlayer() {
		
		table.newPlayer();
		assertEquals(table.getNumPlayers(), 1);
		
	}

}
