package model;

/**
 * Game flow goes from 
 * IDLE 
 * 		Dealer has yet to start the round
 * --> INIT 
 * 		Dealer clicks start button and round is initialized
 * --> BETTING 
 * 		Players place bets within the 20 second limit
 * --> DEALING 
 * 		Dealer deals the initial cards
 * --> PLAYER_ACTIONS 
 * 		Players go, in turn(sequence), and have the following actions
 * 			--> HIT
 * 			--> STAND
 * 			--> SPLIT (if applicable)
 * 			--> DOUBLE_DOWN (if applicable)
 * 		this continues until the player either
 * 			a. Stands -or-
 * 			b. Busts 
 * --> DEALER_ACTION (if applicable)
 * 		if any player has not busted, the dealer will have the following actions
 * 			--> HIT
 * 			--> STAND
 * 		this continues until the dealer either
 * 			a. Stands -or-
 * 			b. Busts
 * --> WINNINGS
 * 		winnings is the state where winners are determined and the winnings of the round 
 * 		are distributed
 * --> IDLE
 * 		after distribution of winnings the IDLE state resumes waiting for the dealer to start
 */
public enum GAME_STATE {
	IDLE,
	INIT,
	BETTING,
	DEALING,
	PLAYER_ACTIONS,
	DEALER_ACTION,
	WINNINGS
}


