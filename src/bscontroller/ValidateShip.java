package bscontroller;

import java.util.Random;

import bsmodel.BsGame;
import bsmodel.BsPlayer;


public class ValidateShip {

	private BsGame game;
	private int inputRow, inputColumn;
	private String inputOrient;
	
	//constructor receives the game and input. Converts the input for the methods to work with
	public ValidateShip(BsGame game, String row, String column, String orient){
		this.game=game;
		this.inputRow=convertRowString(row);
		this.inputColumn=Integer.parseInt(column);
		this.inputOrient=orient;
	}
	//converting the row String to an integer for easier iteration
	private int convertRowString(String row){
		switch(row){
		case "A": return 1;
		case "B": return 2;
		case "C": return 3;
		case "D": return 4;
		case "E": return 5;
		case "F": return 6;
		case "G": return 7;
		case "H": return 8;
		case "I": return 9;
		case "J": return 10;
		default: return -1; 
	}
	}
	
	/*called from the servlet to determine which JSP to got to next */
	public boolean stillMoreUserShips(){
		int currentNumOfShipsSet = game.getUser().getDefBoard().getShipsOnBoard();
		if(currentNumOfShipsSet<5){// if ships on the board is less than 5
			
			//the cpu sets all their ships only once in this block
			if(currentNumOfShipsSet==0 && (game.getCPU().getDefBoard().getShipsOnBoard()!=5)){ 
				setAllShipsOnCPUBoard();
				game.getCPU().getDefBoard().setAmountShipsOnBoard(5);
			}
			
			return true;
		}
		return false;
	}
	public void tryToSetShipOnUserBoard(){
		setUserWarningsToFalse();
		int temp = game.getUser().getDefBoard().getShipsOnBoard();
		int length = game.getUser().getDefBoard().getCurrentShipLength();
		if(shipCanBePlaced(game.getUser(), inputRow, inputColumn, inputOrient, length)){
			setShipOnBoard(game.getUser(), inputRow, inputColumn, inputOrient, length);
			game.getUser().getDefBoard().setAmountShipsOnBoard(++temp);
			changeCurrentShipLength(length); //try to set the ship on the board. if successful decrease the next ship length
			updateShipSetJSPInfo(game.getUser(), inputRow, inputColumn, inputOrient, length);	
			//put the integer codes into the array for the jsp to interpret
		}
	}
	//reduces the length of the next ship, but makes sure 3 is used twice
	public void changeCurrentShipLength(int length){
		boolean place3 = game.getUser().getDefBoard().hasPlacedSize3();
		if((length==3) && !place3){
			game.getUser().getDefBoard().setPlacedSize3(true); 	//place a second ship of size 3
		}else{
			game.getUser().getDefBoard().setCurrentShipLength(--length);
		}
	}
	public void setUserWarningsToFalse(){
		game.getUser().getDefBoard().setOffOfBoard(false);
		game.getUser().getDefBoard().setOverlap(false);
	}
	public void setCPUWarningsToFalse(){
		game.getCPU().getDefBoard().setOffOfBoard(false);
		game.getCPU().getDefBoard().setOverlap(false);
	}
	/* Setting the ships on a user and cpu defense board*/
	public boolean offBoard=false;
	public boolean overlap=false;
	
	private boolean isOrientH(String orient){
		if(orient.equals("horizontal")){
			return true;
		}
		return false;
	}
	private boolean isOrientV(String orient){
		if(orient.equals("vertical")){
			return true;
		}
		return false;
	}
	
	private boolean shipCanBePlaced(BsPlayer player, int row, int column, String orient, int length){
		if(isOrientH(orient)){
			lookHorizontal(player, row, column, length);
		}else if(isOrientV(orient)){
			lookVertical(player, row, column, length);
		}
		if(player.getDefBoard().isOffOfBoard() || player.getDefBoard().isOverlap()){
			return false;
		}
		return true;
	}
	//look at the spaces the ship will occupy. Do they exist and are they unoccupied?
	//looking horizontally
	public void lookHorizontal(BsPlayer player, int row, int column, int length){
		for(int i=0; i<length; i++){
			try{
				if(!player.getDefBoard().getSquare(row,column+i).equals("|__")){
					game.getUser().getDefBoard().setOverlap(true);
					game.getCPU().getDefBoard().setOverlap(true);
					break;
				}
			}catch(ArrayIndexOutOfBoundsException exc){
				game.getUser().getDefBoard().setOffOfBoard(true);
				game.getCPU().getDefBoard().setOffOfBoard(true);
				break;
			}
		}
	}
	//looking vertically
	public void lookVertical(BsPlayer player, int row, int column, int length){
		for(int i=0; i<length; i++){
			try{
				if(!player.getDefBoard().getSquare(row+i,column).equals("|__")){
					game.getUser().getDefBoard().setOverlap(true);
					game.getCPU().getDefBoard().setOverlap(true);
					break;
				}
			}catch(ArrayIndexOutOfBoundsException exc){
				game.getUser().getDefBoard().setOffOfBoard(true);
				game.getCPU().getDefBoard().setOffOfBoard(true);
				break;
			}
		}
	}
	//set a ship of any length, anywhere, on either player's board
	public boolean setShipOnBoard(BsPlayer player, int row, int column, String orient, int length){
		
		try{
			//placing the ships if they can be placed
			if(shipCanBePlaced(player, row, column, orient, length) &&  isOrientH(orient)){
				for(int i=0; i<length; i++){
					player.getDefBoard().setSquare(row, column+i, "| @");
				}
				return true;
			}else if(shipCanBePlaced(player, row, column, orient, length) && isOrientV(orient)){
				for(int i=0; i<length; i++){
					player.getDefBoard().setSquare(row+i, column, "| @");
				}
				return true;
			}
		}catch(ArrayIndexOutOfBoundsException exc){
			game.getUser().getDefBoard().setOffOfBoard(true);
		}
		return false;
		
	}
	
	//getting random input
	private int cpuPlaceRow, cpuPlaceColumn, cpuIntOrient;
	private String cpuPlaceOrient;
	public void getRandomCPUInput(){
		Random rand = new Random();
		cpuPlaceRow = rand.nextInt(10) + 1;
		cpuPlaceColumn = rand.nextInt(10) + 1;
		cpuIntOrient = rand.nextInt(2);
		if (cpuIntOrient == 1) {
			cpuPlaceOrient = "vertical";
		} else {
			cpuPlaceOrient = "horizontal";
		}
	}
	
	//setting all of the CPU ships on the board in one method
	public void setAllShipsOnCPUBoard(){
		boolean firstThree=true;
		int i=5;
		for(int j=0;j<5;j++){
			do{
				setCPUWarningsToFalse();
				getRandomCPUInput();
			}while(!setShipOnBoard(game.getCPU(), cpuPlaceRow, cpuPlaceColumn, cpuPlaceOrient, i));
			if(i==3 && firstThree){//make sure to place a ship of size 3 twice
				i=4;
				firstThree=false;
			}//decrease the size of the ship to place
			i--;
		}
	}

	// 20 -> water image
	// 21 -> end face north
	// 22 -> end face east
	// 23 -> end face south
	// 24 -> end face west
	// 25 -> middle horizontal
	//26 -> middle vertical
	private void updateShipSetJSPInfo(BsPlayer p, int r, int c, String orient, int length){
		if(isOrientV(orient)){//if the ship is moving down
			p.setJspInfo(r, c, 21);//the starting space gets the "end face north" integer code
			for(int i=r+1;i<=(r+length-2);i++){	//for loop starts does not include ends of the ship
				p.setJspInfo(i, c, 25);	//assign horizontal integer code
			}
			p.setJspInfo(r+length-1, c, 23); //the ending space gets the "end face south" integer code
			
		}else if(isOrientH(orient)){//if the ship is moving right
			p.setJspInfo(r, c, 24); //the starting space gets the "end face west" integer code
			for(int i=c+1;i<=(c+length-2);i++){	//for loop starts does not include ends of the ship
				p.setJspInfo(r, i, 26);	//assign horizontal integer code
			}
			p.setJspInfo(r, c+length-1, 22); //the ending space gets the "end face east" integer code
		}
	}
	
	/*the other method the Servlet calls to display JSP boards to the view */
	public int[][] getBoardInfoArray(){
		return game.getUser().getJspInfo();
	}
	
}
