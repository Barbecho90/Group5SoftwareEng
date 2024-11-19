package game;

import model.Lobby;
import model.Shoe;
import model.Table;

public class GameManager {
	
	private static GameManager instance = null;
	private Lobby lobby;    //Manages players joining/leaving game
	private Table table;	//manages game rules and logic
	private Shoe shoe;		//manages the deck of cards.
	
	private GameManager() {
		
	}
	 public GameManager(Lobby lobby, Table table, Shoe shoe) {
	        this.lobby = lobby;
	        this.table = table;
	        this.shoe = shoe;
	    }
	
	public static GameManager getInstance() {
		if(instance == null) {
			instance = new GameManager();
			
		}
		return instance;
	}
	
	
    // Method to initialize the game
    public void startGame() {
        System.out.println("Starting the blackjack game...");
        // Add game initialization logic here
    }

    // Method to handle game logic
    public void playGame() {
        System.out.println("Playing the blackjack game...");
        // Add main game loop logic here
    }

    // Method to end the game
    public void endGame() {
        System.out.println("Ending the blackjack game...");
        // Add cleanup logic here
    }
	

}
