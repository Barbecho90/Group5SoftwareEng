package modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.AbstractTable;
import model.Dealer;
import model.Player;

public class TableTest {
	private AbstractTable table;
    private Dealer mockDealer;
    private Player mockPlayer;
    private Player mockPlayer2;
    private class ConcreteTable extends AbstractTable {
        public ConcreteTable(Dealer dealer) {
            super(dealer);
        }
    }
    @Before
    public void setUp() {
        
    	mockDealer = new Dealer(); 
       table = new ConcreteTable(mockDealer);
       System.out.println(table);
       mockPlayer = new Player();
      // mockPlayer2 = new Player();
       System.out.println(table);

         }
	//test constructor, getID(), getNumPlayers(), getMinBet()
	
    @Test
	public void TestNewTable() {
		
		assertEquals(table.getId(), "T000");
		assertEquals(table.getNumPlayers(), 0);
		assertEquals(table.getMinBet(), 2);
		
	}
	
	
	//tests if number of players updates when new player joins, newPlayer(), 
	@Test
	public void TestJoinTable() {
		
		table.joinTable(mockPlayer);
		table.joinTable(mockPlayer);
		assertEquals(table.getNumPlayers(), 2);
		
	}
	@Test
    public void testIsOpen() {
        table.joinTable(mockPlayer);  // Add one player
        assertTrue("Table Open", table.isOpen());
    }


}
