package model;

import java.io.Serializable;

public class LobbyTable implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String tableId;
	private int playerCount;
	private int minBet;
	
	public LobbyTable() {}
	
	public LobbyTable(String tableId, int playerCount, int minBet) {
		this.setTableId(tableId);
		this.setPlayerCount(playerCount);
		this.setMinBet(minBet);
	}

	public int getMinBet() {
		return minBet;
	}

	public void setMinBet(int minBet) {
		this.minBet = minBet;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
	@Override
	public String toString() {
		return "Table: " + getTableId() + " - Players: " + getPlayerCount() + "/5 - Min Bet: " + getMinBet();
	}
}
