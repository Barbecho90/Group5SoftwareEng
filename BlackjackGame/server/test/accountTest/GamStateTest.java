package accountTest;


import org.junit.Before;
import org.junit.Test;

import gameState.GameState;
import model.Account;
import model.Dealer;
import model.Player;
import model.ROLE;
import model.Shoe;
import serverModel.Table;
import model.AbstractTable;
import table.TableManager;


class GameStateTest {

    private GameState gameState;
    private AbstractTable table;
    private Dealer dealer;
    private Player player1, player2;
    private Shoe shoe;
    private Account account1, account2,dealerAccount;
 
    @Before
    void setUp() {
    	dealerAccount = new Account("dealer", "password", ROLE.DEALER);
    	System.out.println(dealerAccount + "\n");
    	dealer = (Dealer) dealerAccount.getUser();
    	System.out.println(dealer + "\n");
    	account1 = new Account("player1", "password", ROLE.PLAYER);
        account2 = new Account("player2", "password", ROLE.PLAYER);

    	
    	player1 = (Player) account1.getUser();
        player2 =(Player) account2.getUser();
        
        player1.getAccount().deposit(1000);
        player2.getAccount().deposit(1000);

        shoe = new Shoe();
    	table = new Table(dealer);
    	table.setShoe(shoe);
    	
    	TableManager.getInstance().createTable(dealer);
    	TableManager.getInstance().joinTable(table.getId(), player1);
    	TableManager.getInstance().joinTable(table.getId(), player2);
    	
    	
        gameState = new GameState(table);
        
        
        
        
    }

//    @Test
//    void testStartRound() {
//        // Start the game round
//        gameState.startRound();
//
//        // Validate dealer and player hands have two cards each
//        assertEquals(2, gameState.getDealerHand().getTotalCards());
//        assertEquals(2, gameState.getCurrentPlayerHand().getTotalCards());
//    }
//
//    @Test
//    void testPlayerActions() {
//        // Start the round
//        gameState.startRound();
//
//        // Test hit action
//        Hand playerHand = gameState.getCurrentPlayerHand();
//        int initialSize = playerHand.getTotalCards();
//        gameState.getCurrentPlayerHand().hit(shoe);
//
//        assertEquals(initialSize + 1, playerHand.getTotalCards());
//
//        // Test if bust status updates correctly
//        while (!playerHand.isBusted()) {
//            playerHand.hit(shoe);
//        }
//        assertTrue(playerHand.isBusted());
//    }
//
//    @Test
//    void testNextPlayer() {
//        gameState.startRound();
//
//        // Ensure the first player is active
//        assertEquals(player1, table.getPlayerList().get(0));
//
//        // Move to the next player
//        gameState.nextPlayer();
//
//        // Ensure the second player is active
//        assertEquals(player2, table.getPlayerList().get(1));
//    }
//
//    @Test
//    void testDealerPlay() {
//        // Start the round
//        gameState.startRound();
//
//        // Move to the dealer's turn
//        gameState.nextPlayer();
//        gameState.nextPlayer(); // Assuming two players
//
//        // Ensure dealer follows rules (e.g., hitting until 17 or higher)
//        Hand dealerHand = gameState.getDealerHand();
//        assertTrue(dealerHand.getHandValue() >= 17 || dealerHand.isBusted());
//    }
//
//    @Test
//    void testDetermineWinners() {
//        gameState.startRound();
//
//        // Simulate game end
//        while (gameState.isRoundActive()) {
//            gameState.nextPlayer();
//        }
//
//        // Validate winners (logic should depend on your determineWinners implementation)
//        assertNotNull(gameState.getDealerHand());
//        assertNotNull(gameState.getCurrentPlayerHand());
//    }
//
//    @Test
//    void testResetGame() {
//        gameState.startRound();
//
//        // Reset the game
//        gameState.resetGame();
//
//        // Validate that all hands and game states are reset
//        assertFalse(gameState.isRoundActive());
//        assertEquals(0, gameState.getDealerHand().getTotalCards());
//        assertEquals(0, gameState.getCurrentPlayerHand().getTotalCards());
//    }
}
