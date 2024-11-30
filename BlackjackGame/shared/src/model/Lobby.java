package model;

import java.util.ArrayList;

public class Lobby {
	//Main lobby should be initialized within the server once it's running
	ArrayList<AbstractTable> tables = new ArrayList<AbstractTable>();
	int numTables = 0;
	int availableDealers = 0;
	
	public Lobby() {
		this.tables = new ArrayList<AbstractTable>();
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
	public ArrayList<AbstractTable> getTableList(){
		return this.tables;
	}
	
	public int getNumTables() {
		return this.numTables;
	}
	
	public int getAvailableDealers() {
		return this.availableDealers;
	}
	
	
}
