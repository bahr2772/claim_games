package BJmodel;

import java.util.ArrayList;

//Player is part of the Model

public class Player {
	private String name;
	private int chipCount;
	private int currentHandCount;
	private ArrayList<Card> playerHand;
	
	public Player(String name, int chipCount) {
		this.name = name;
		this.chipCount = chipCount;
		playerHand = new ArrayList<Card>();
	}
	
	public void setChipCount(int chipCount) {
		this.chipCount = chipCount;
	}
	
	public int getChipCount() {
		return this.chipCount;
	}
	
	public void setCurrentHandCount(int currentHandCount) {
		this.currentHandCount = currentHandCount;
	}
	
	public int getCurrentHandCount() {
		return this.currentHandCount;
	}
	
	public ArrayList<Card> getPlayerHand() {
		return this.playerHand;
	}
	
	public boolean bust() {
		return this.getCurrentHandCount() > 21;
	}
	
}
