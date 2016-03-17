package bsmodel;

public class BsGame {

	private BsPlayer user;
	private BsPlayer cpu;
	private boolean isGameOver=false;
	private boolean isUserTurn=false;
	private int shipConfigNum=5;
	
	public BsGame(String turn){
		user=new BsPlayer();
		cpu=new BsPlayer();
		if(turn.equals("before")){
			isUserTurn=true;
		}
	}
	
	public BsPlayer getUser(){
		return user;
	}
	public BsPlayer getCPU(){
		return cpu;
	}
	public void setGameOver(boolean status){
		isGameOver=status;
	}
	public boolean getGameStatus(){
		return isGameOver;
	}

	public boolean isUserTurn() {
		return isUserTurn;
	}

	public void setUserTurn(boolean isUserTurn) {
		this.isUserTurn = isUserTurn;
	}

	public int getShipConfigNum() {
		return shipConfigNum;
	}

	public void setShipConfigNum(int shipConfigNum) {
		this.shipConfigNum = shipConfigNum;
	}

	

	
}
