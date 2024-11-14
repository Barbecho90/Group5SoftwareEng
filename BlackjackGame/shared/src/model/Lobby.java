package model;

import java.util.*;

public class Lobby {
	//Main lobby should be initialized within the server once it's running
	ArrayList<Table> Tables = new ArrayList<Table>();
	int numTables = 0;
	int availableDealers = 0;
	
	public Lobby() {
		
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
	public ArrayList<Table> getTableList(){
		return this.Tables;
	}
	
	public int getNumTables() {
		return this.numTables;
	}
	
	public int getAvailableDealers() {
		return this.availableDealers;
	}
	
	
}
