package serverModel;

import java.util.ArrayList;
import java.util.List;

import model.AbstractTable;
import model.Dealer;
import model.Hand;
import model.Player;

public class Table extends AbstractTable {
	private static final long serialVersionUID = 1L;
	private GAME_STATE state;
	
	private List<Player> turnOrder;
	

	public Table(Dealer dealer) {
		super(dealer); // Uses the superclass constructor (AbstractTable) to be able to get all the
						// functionality of the AbstractTable

		this.state = GAME_STATE.IDLE;
		this.turnOrder = new ArrayList<Player>();
	}

	public void startRound() {
		if (state != GAME_STATE.IDLE) {
			throw new IllegalStateException("Round already in progress");
		}
		state = GAME_STATE.INIT;
		turnOrder.clear();
		turnOrder.addAll(players);
		
		// Make sure everything is empty
		this.hostDealer.setDealerHand(new Hand());

		for (Player player : this.players) {
			player.setHand(new Hand());
		}

		waitForBets();
	}

	private void waitForBets() {
		state = GAME_STATE.BETTING;

		//
		System.out.println("Waiting for bets....");
		
		for (Player player: turnOrder) {
			// Set player bet to zero, send the command to place bet
		}
		
		dealInitialCards();
	}

	private void dealInitialCards() {
		if (state != GAME_STATE.BETTING) {
			throw new IllegalStateException("Cannot deal cards unless in BETTING state");
		}
		state = GAME_STATE.DEALING;
		for (Player player : this.players) {
			player.getHand().addCard(getDealerShowCard());
			player.getHand().addCard(getDealerShowCard());
		}

		Hand dealerHand = this.hostDealer.getDealerHand();
		dealerHand.addCard(getDealerShowCard());

		System.out.println("Cards dealt. Players and dealer have their initial hands.");
		startPlayerActions();
	}

	private void startPlayerActions() {
	        state = GAME_STATE.PLAYER_ACTIONS;

	        for (Player player : this.players) {
	            while (true) {
	                // Simulate player actions (e.g., HIT, STAND, SPLIT, DOUBLE_DOWN)
	                String action = player.decideAction(); // Assuming players decide actions programmatically
	                if ("HIT".equals(action)) {
	                    player.getHand().addCard(this.hostDealer.getNextCard());
	                    if (player.getHand().isBusted()) {
	                        System.out.println(player.getId() + " busted!");
	                        break;
	                    }
	                } else if ("STAND".equals(action)) {
	                    System.out.println(player.getId() + " stands.");
	                    break;
	                }
	                // Additional actions like SPLIT or DOUBLE_DOWN can be added here
	            }
	        }
	        handDealerAction();
	}

	private void handDealerAction() {
		state = GAME_STATE.DEALER_ACTION;

	}

	private void winners() {
		state = GAME_STATE.WINNINGS;

	}

	// Dealer ready for next round
	private void resetRound() {
		state = GAME_STATE.IDLE;
		System.out.println("Round over. Ready for the next round.");
	}

	public GAME_STATE getState() {
		return this.state;
	}
}
