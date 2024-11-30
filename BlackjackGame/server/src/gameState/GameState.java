package gameState;

import java.util.ArrayList;

import model.Constants;
import model.Dealer;
import model.Hand;
import model.Player;
import model.Shoe;
import serverModel.GAME_STATE;
import model.AbstractTable;

public class GameState {
	private AbstractTable table;
	private Dealer dealer;
	private ArrayList<Player> players;
	private ArrayList<Hand> playerHands;
	private Hand dealerHand;
	private int currentPlayerIndex;
	private boolean roundActive;
	private Shoe shoe;
	private GAME_STATE currentState;

	public GameState(AbstractTable table) {
		this.table = table;
		this.dealer = table.getDealer();
		this.players = table.getPlayerList();
		this.playerHands = new ArrayList<>();
		this.dealerHand = new Hand();
		this.currentPlayerIndex = 0;
		this.roundActive = false;
		this.shoe = table.getShoe();
		this.currentState = GAME_STATE.IDLE;
		// Initialize player hands
		for (Player player : players) {
			playerHands.add(new Hand());

		}

	}

	public void startRound() {
		if (roundActive) {
			throw new IllegalStateException("Round already in progress");

		}
		roundActive = true;
		dealerHand = new Hand();
		currentPlayerIndex = 0;

		for (int i = 0; i < players.size(); i++) {
			playerHands.get(i).clear();
			playerHands.get(i).addCard(shoe.dealNextCard());
			playerHands.get(i).addCard(shoe.dealNextCard());
		}

		// Deal two cards to the dealer
		dealerHand.addCard(shoe.dealNextCard());
		dealerHand.addCard(shoe.dealNextCard());
		
		System.out.println("Dealer's cards: " + dealerHand);
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Player " + players.get(i).getId() + "'s cards: " + playerHands.get(i));
		}

	}

	public Hand getCurrentPlayerHand() {
		return playerHands.get(currentPlayerIndex);
	}

	// Move to the next player
	public void nextPlayer() {
		currentPlayerIndex++;
		if (currentPlayerIndex >= players.size()) {
			// End of player turns, dealer plays
			dealerPlay();
		}
	}

	// Dealer's turn logic
	private void dealerPlay() {
		while (dealerHand.getHandValue() < 17) {
			dealerHand.addCard(shoe.dealNextCard());
		}
		roundActive = false;
		determineWinners();
	}

	// Determine winners and handle outcomes
	private void determineWinners() {
		int dealerValue = dealerHand.getHandValue();
		boolean dealerBust = dealerValue > Constants.MAX_HAND_VALUE;

		for (int i = 0; i < players.size(); i++) {
			Hand playerHand = playerHands.get(i);
			int playerValue = playerHand.getHandValue();
			boolean playerBust = playerValue > Constants.MAX_HAND_VALUE;

			if (playerBust || (!dealerBust && dealerValue >= playerValue)) {
				System.out.println(players.get(i).getId() + " loses.");
			} else {
				System.out.println(players.get(i).getId() + " wins!");
			}
		}
	}

	// Get dealer's hand
	public Hand getDealerHand() {
		return dealerHand;
	}

	// Check if the round is active
	public boolean isRoundActive() {
		return roundActive;
	}

	// Reset the game state for a new game
	public void resetGame() {
		roundActive = false;
		currentPlayerIndex = 0;
		dealerHand.clear();
		for (Hand hand : playerHands) {
			hand.clear();
		}
	}
}