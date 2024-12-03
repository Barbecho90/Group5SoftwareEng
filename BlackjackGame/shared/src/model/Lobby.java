package model;

import java.io.Serializable;
import java.util.ArrayList;

import observable.Observable;

public class Lobby extends Observable implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static Lobby instance = null;
	//Main lobby should be initialized within the server once it's running
	ArrayList<LobbyTable> tables = new ArrayList<LobbyTable>();
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Dealer> dealers = new ArrayList<Dealer>();
	
	int numTables = 0;
	int availableDealers = 0;
	
	private Lobby() {
		this.tables = new ArrayList<LobbyTable>();
	}
	
	public static Lobby getInstance() {
		if(instance == null) {
			instance = new Lobby();
		}
		
		return instance;
	}
	
	//update dealer count
	public void newDealer() {
		this.availableDealers++;
	}
	//update table count
	public void newTable() {
		this.numTables++;
	}
	
	
	//getters
	public ArrayList<LobbyTable> getTableList(){
		return this.tables;
	}
	
	public int getNumTables() {
		return this.tables.size();
	}
	
	public int getAvailableDealers() {
		return this.availableDealers;
	}
	
	public void addNewTable(LobbyTable table) {
		this.tables.add(table);
	}
	
	public void addPlayerToLobby(Player player) {
		this.players.add(player);
	}
	
	public void removePLayerFromLobby(Player player) {
		this.players.remove(player);
	}
	
	public void addDealerToLobby(Dealer dealer) {
		this.dealers.add(dealer);
	}
	
	public void removeDealerFromLobby(Dealer dealer) {
		this.dealers.remove(dealer);
	}
}
