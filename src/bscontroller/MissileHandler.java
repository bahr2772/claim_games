package bscontroller;

import java.util.Random;

import bsmodel.BsBoard;
import bsmodel.BsGame;
import bsmodel.BsPlayer;

public class MissileHandler {
	private BsGame game;
	private int fireRow;
	private int fireColumn;
	
	//constructor for firing missile
	public MissileHandler(String row, String column, BsGame game){
		fireRow = Integer.parseInt(row);
		fireColumn = Integer.parseInt(column);
		this.game=game;
	}
	
	//checking to see if the game is over 
	private void isGameOver(){
		if(game.getUser().getShipSpacesLeftToGet()==0){
			game.setGameOver(true);
			game.getUser().setWinStatus(true);
		}else if(game.getCPU().getShipSpacesLeftToGet()==0){
			game.setGameOver(true);
			game.getCPU().setWinStatus(true);
		}
	}
	//either the user or the cpu fires a missile
	//the jsp will either display 100 buttons or 100 images that can't be clicked depending on who is about to fire a missile
	public void fireSomeMissile(){
		if(game.getUser().isGoesFirst()){
			userChoseToFireFirst();
		}else{							//these 2 methods only let the CPU fire when they are either 1 behind or equal in shots fired
			userChoseToFireSecond();
		}
		isGameOver();
	}
	//these 2 methods below exist so that the firing page can be refreshed and the computer doesn't just keep firing when it's not its turn
	private void userChoseToFireFirst(){
		if(game.getUser().isTurnToShoot()){ //if it is the user's turn to shoot
			if(userFireMissile()){				// if the user clicked a blue square
				game.getUser().setTurnToShoot(false);	
				game.getCPU().setTurnToShoot(true);		//set the other player's turn for next time the method is called
			}
		}else if(game.getUser().getNumberOfShots() == (game.getCPU().getNumberOfShots()+1)){	//the computer fires if they 1 less shot on board
			fireCPUMissile(game.getCPU(), game.getUser());
			game.getUser().setTurnToShoot(true);	
			game.getCPU().setTurnToShoot(false);
		}
	}
	private void userChoseToFireSecond(){
		if(game.getUser().isTurnToShoot()){ //if it is the user's turn to shoot
			if(userFireMissile()){				// if the user clicked a blue square
				game.getUser().setTurnToShoot(false);	
				game.getCPU().setTurnToShoot(true);		//set the other player's turn for next time the method is called
			}
		}else if(game.getUser().getNumberOfShots() == game.getCPU().getNumberOfShots()){	//the computer fires if they have same amount shots on board
			fireCPUMissile(game.getCPU(), game.getUser());
			game.getUser().setTurnToShoot(true);	
			game.getCPU().setTurnToShoot(false);
		}
	}
	private boolean userFireMissile(){
		return markOffBoard(fireRow, fireColumn, game.getUser(), game.getCPU().getDefBoard());
	}
	//the offensive player fires at the defensive player's board and returns true and marks X or O if successful
	private boolean markOffBoard(int row, int column, BsPlayer offPlayer, BsBoard defBoard){
		int shots = offPlayer.getNumberOfShots();
		if(!isSquareOffBoard(row, column, defBoard)){	//does the square exist? (not off the board)
			if(isSquareBlank(row, column, offPlayer.getOffBoard())){	//does your offense board not already have a strike on it
				offPlayer.setNumberOfShots(++shots); 	//the player's model int variable is increased CRUCIAL
				
				if(squareHasShip(row, column, defBoard)){//is the square occupied by an opponent's ship?
					offPlayer.getOffBoard().setSquare(row, column, "| X");//mark the player's offensive board with X on that space
					offPlayer.decreaseShipSpaces();					//decrease the player's ships to get by 1
					markJSPIntCode(offPlayer, row, column, true);
					return true;
				}else{
					offPlayer.getOffBoard().setSquare(row, column, "| O");//mark the player's offensive board with O on that space
					markJSPIntCode(offPlayer, row, column, false);
					return true;
				}
			}
		}
		return false;
	}
	/*
	 * JSP image codes 
	 * 50 -> blue button
	 * 51 -> hit image X
	 * 52 -> miss image O
	 * 53 -> explosion image (initial hit)
	 * 54 -> splash image (initial miss)
	 */
	//sets JSP image code for the defensive board (the one with your ships being fired at)
	private void markJSPIntCode(BsPlayer player, int row, int column, boolean wasHit){
		int temp = player.getNumberOfShots();
		boolean finalShot = (player.getShipSpacesLeftToGet()==0) ? true:false;
		if(player.equals(game.getUser())){	//swap the player passed in with the other player so the correct player's JSP code is used
			player = game.getCPU();
		}else{
			player = game.getUser();
		}
		// the x and o image for the shot from last time
		if (temp > 1) {
			if (player.isLastShotWasHit()) {		//if the last fire was a hit
				player.setJspInfo(player.getRowFromLastTime(), player.getColumnFromLastTime(), 51);	//mark the square from last time to x
			} else {	//if the last fire was a miss
				player.setJspInfo(player.getRowFromLastTime(), player.getColumnFromLastTime(), 52);	//mark the square from last time as o
			}
		}
		thisRoundJspImage(player, row, column, wasHit, finalShot);
	}
	private void thisRoundJspImage(BsPlayer player, int row, int column, boolean wasHit, boolean finalShot){
		// the explosion and splash image for this round
		if (wasHit && !finalShot) {
			player.setJspInfo(row, column, 53); // explosion (hit)
			player.setLastShotWasHit(true);
		} else if (!wasHit && !finalShot) {
			player.setJspInfo(row, column, 54); // splash (miss)
			player.setLastShotWasHit(false);
		} else if (wasHit) {
			player.setJspInfo(row, column, 51);
		} else {
			player.setJspInfo(row, column, 52);
		}
		// set the "last time" row and column for the next turn
		player.setRowFromLastTime(row);
		player.setColumnFromLastTime(column);
	}
	
	
	//returns true if the square can't be fired at (doesn't exist or is a border label square)
	private boolean isSquareOffBoard(int row, int column, BsBoard board){
		if(board.getSquare(row, column).equals("|__")){
			return false;
		}else if(board.getSquare(row, column).equals("| @")){
			return false;
		}else{
			return true;
		}
	}
	private boolean isSquareBlank(int row, int column, BsBoard board){
		if(board.getSquare(row, column).equals("|__")){
			return true;
		}
		return false;
	}
	//returns true if the square is occupied by a ship
	private boolean squareHasShip(int row, int column, BsBoard board){
		if(board.getSquare(row, column).equals("| @")){
			return true;
		}else{
			return false;
		}
	}
	/* Methods exclusive to CPU Firing Artificial Intelligence */
		//the CPU firing AI methods all in this method
		public void fireCPUMissile(BsPlayer cpu, BsPlayer user){
			boolean isShotFired = false;
			Random rand = new Random();
			int r=1, c=1;
			//while a shot hasn't been fired, try to fire a shot
			while(!isShotFired){
				r=rand.nextInt(10)+1;
				c=rand.nextInt(10)+1;
				if(game.getCPU().isLineAfterMiss()){		//
					isShotFired=resumeTheLine(cpu, user);
				}else if(game.getCPU().isPursuingLine()){	//shooting down the line of 2 consecutive hits
					isShotFired = fireOnLine(cpu, user);
				}else if(game.getCPU().isSingleStrikeWasHit()){	//searching vertically and horizontally from the random hit
					isShotFired=fireAroundLastShot(game.getCPU().getFixedRow(), game.getCPU().getFixedColumn(), cpu, user);
				}else if(game.getCPU().isTwoHitNeighbor()){ //if a space exists that borders 2 hits either right, left, up, or down
					isShotFired=fireAtTwoHitsNeighbor(cpu, user);
				}else{												//fire randomly (last resort)
					isShotFired=fireCPURandomly(r, c, cpu, user);
				}
			}
			//potentially changing attack strategy
			checkTheMarkForStrategyChange(r, c, cpu);
		}

		private void checkTheMarkForStrategyChange(int row, int column, BsPlayer cpu) {
			cpu.setLineAfterMiss(false);
			// if the CPU gets a hit on a random shot, saves the row and column of
			// that hit for the strategy next turn
			if (!cpu.isPursuingLine() && !cpu.isSingleStrikeWasHit() && cpu.getOffBoard().getSquare(row, column).equals("| X")) {
					cpu.setSingleStrikeWasHit(true);
					cpu.setFixedRow(row);
					cpu.setFixedColumn(column);
				
			}else if(!cpu.isPursuingLine() && !cpu.isSingleStrikeWasHit() && (cpu.getShipSpacesLeftToGet()<16)){
				cpu.setTwoHitNeighbor(true); //check for squares bordering 2 strikes before firing randomly
			}
			if(cpu.isSingleStrikeWasHit() && singleStrikeSurroundsOtherStrikes(cpu, row, column)){
				cpu.setSingleStrikeWasHit(false);
			}
			// if the line the CPU is pursuing, didn't get a hit this time
			if (cpu.isPursuingLine() && cpu.getOffBoard().getSquare(cpu.getMarkedRow(), cpu.getMarkedColumn()).equals("| O")) {
				// if the CPU can't reverse the line, stop using pursue the line strategy
				cpu.setPursuingLine(tryToReverseTheLine(cpu));
			}
		}
		//returns false if the line of attack could not be reversed
		public boolean tryToReverseTheLine(BsPlayer cpu){
			if(cpu.getFixedRow()==cpu.getAdvRow()){//reversing the column path
				if((cpu.getFixedColumn() > cpu.getAdvColumn()) && (cpu.getFixedColumn()+1 < 11)){
					cpu.setAdvColumn(cpu.getFixedColumn()+1);
					if(!cpu.getOffBoard().getSquare(cpu.getAdvRow(), cpu.getAdvColumn()).equals("|__")){//if other side of line is not open
						return false;
					}
				}else if(cpu.getFixedColumn() < cpu.getAdvColumn() && (cpu.getFixedColumn()-1 > 0)){
					cpu.setAdvColumn(cpu.getFixedColumn()-1);	
					if(!cpu.getOffBoard().getSquare(cpu.getAdvRow(), cpu.getAdvColumn()).equals("|__")){//if other side of line is not open
						return false;
					}
				}else{
					return false;
				}
			}else if(cpu.getFixedColumn()==cpu.getAdvColumn()){//reversing the row path
				if(cpu.getFixedRow() > cpu.getAdvRow() && (cpu.getFixedRow()+1 < 11)){
					cpu.setAdvRow(cpu.getFixedRow()+1);
					if(!cpu.getOffBoard().getSquare(cpu.getAdvRow(), cpu.getAdvColumn()).equals("|__")){//if other side of line is not open
						return false;
					}
				}else if(cpu.getFixedRow() < cpu.getAdvRow() && (cpu.getFixedRow()-1 > 0)){
					cpu.setAdvRow(cpu.getFixedRow()-1);
					if(!cpu.getOffBoard().getSquare(cpu.getAdvRow(), cpu.getAdvColumn()).equals("|__")){//if other side of line is not open
						return false;
					}
				}else{
					return false;
				}
			}else{
				return false;
			}
			cpu.setLineAfterMiss(true);
			return true;
		}
		
		//when the cpu is firing down a line and misses, it will try to reverse the line
		//if there is a space available on the other end, it will call this method
		//if this method didn't exist, it would skip an extra space
		public boolean resumeTheLine(BsPlayer cpu, BsPlayer user){
			cpu.setMarkedRow(cpu.getAdvRow());
			cpu.setMarkedColumn(cpu.getAdvColumn());
			return markOffBoard(cpu.getMarkedRow(), cpu.getMarkedColumn(), cpu, user.getDefBoard());
		}
		
		public boolean fireCPURandomly(int r, int c, BsPlayer cpu, BsPlayer user){//firing totally randomly, returns true if successfully marked
			int temp = cpu.getNumberOfShots();
			if(cpu.getOffBoard().getSquare(r, c).equals("|__")){//if the space is available and could be a hit
				switch(getBorderedMisses(cpu, r, c)){
					case 0: 								//if the square borders 0 misses or edges, mark the square
						markOffBoard(r, c, cpu, user.getDefBoard());return true;
					case 1: 
						if(temp>5){							//if the square borders 1 miss or edge, mark the square after 5 shots
							markOffBoard(r, c, cpu, user.getDefBoard());return true;
						}
					case 2:									//if the square borders 2 misses or edges, mark the square after 15 shots
						if(temp>15){
							markOffBoard(r, c, cpu, user.getDefBoard());return true;
						}
					case 3:									//if the square borders 3 misses or edges, mark the square after 40 shots
						if(temp>40){
							markOffBoard(r, c, cpu, user.getDefBoard());return true;
						}
					case 4: return false;
				}
			}//the randomly generated space is already occupied by a strike
			return false;
			
		}
		//called when a single shot was a strike
		public boolean fireAroundLastShot(int r, int c, BsPlayer cpu, BsPlayer user){//returns true if searched shot was fired
			if(singleStrikeSurroundsOtherStrikes(cpu, r, c)){	//if none of the 4 squares are available, abandon the strategy
				cpu.setSingleStrikeWasHit(false);
				return false;
			}
			Random rand = new Random();
			int square=rand.nextInt(4)+1;
			switch(square){//change either the row or column by 1 and set the advancing row and column for a potential pursuing line strategy
				case 1:r+=1;cpu.setAdvRow(r);cpu.setAdvColumn(c);break;
				case 2:r-=1;cpu.setAdvRow(r);cpu.setAdvColumn(c);break;
				case 3:c+=1;cpu.setAdvRow(r);cpu.setAdvColumn(c);break;
				case 4:c-=1;cpu.setAdvRow(r);cpu.setAdvColumn(c);break;
			}
			if(cpu.getOffBoard().getSquare(r, c).equals("|__")){	//if the square is open
				markOffBoard(r, c, cpu, user.getDefBoard());	//mark the square
				if(cpu.getOffBoard().getSquare(r, c).equals("| X")){	//if the mark was a hit (this would be the second hit in the line)
					cpu.setMarkedRow(cpu.getAdvRow());				//the marked row and column need to be updated for the new line so the new line isn't abandoned after the second hit
					cpu.setMarkedColumn(cpu.getAdvColumn());
					cpu.setPursuingLine(true);	//going to the line strategy next round
					cpu.setSingleStrikeWasHit(false);	//making sure the new strategy is used next round
					cpu.setMarkedRow(0);				//reset the marked column and row to be off the board
					cpu.setMarkedColumn(0);				//so strategy change won't interfere at first
				}
				return true;
			}else{
				return false;
			}
		}
		
		//returns true if a strike was fired successfully
		public boolean fireOnLine(BsPlayer cpu, BsPlayer user){
			//save the stationary rows and columns for a potential line reversal later
			cpu.setMarkedColumn(cpu.getFixedColumn());
			cpu.setMarkedRow(cpu.getFixedRow());
			
			/* VERTICAL LINE PATH STRATEGY */
			if(cpu.getFixedRow() < cpu.getAdvRow()){//if the path is vertical and moving down
				return findNextMovingDown(cpu, user);
			}else if(cpu.getFixedRow() > cpu.getAdvRow()){//if the path is vertical and moving up
				return findNextMovingUp(cpu, user);
				
			/*  HORIZONTAL LINE PATH STRATEGY */ 	
			}else if(cpu.getFixedColumn() < cpu.getAdvColumn()){//if the path is horizontal and moving right
				return findNextMovingRight(cpu, user);
			}else if(cpu.getFixedColumn() > cpu.getAdvColumn()){//if the path is horizontal and moving left
				return findNextMovingLeft(cpu, user);
				
			}else{//So this method was called and there isn't a line to pursue? This should never happen.
				cpu.setPursuingLine(false);
				return false;
			}
		}
		//these next 4 methods return true if they could mark a blank square next on the line of fire
		private boolean findNextMovingDown(BsPlayer cpu, BsPlayer user){
			int tempRow=0;	//a temp row or column is needed to change the fixed row/column if the line needs to be reversed
			//then when the strategy change method tries to reverse the line again, it should fail and move on to random firing
			if(cpu.getOffBoard().getSquare(cpu.getAdvRow()+1, cpu.getFixedColumn()).equals("|__")){//if one below on the column is empty, mark that square
				cpu.setAdvRow(cpu.getAdvRow()+1);
				cpu.setMarkedRow(cpu.getAdvRow());
				markOffBoard(cpu.getMarkedRow(), cpu.getMarkedColumn(), cpu, user.getDefBoard());
				return true;
			}else if(cpu.getOffBoard().getSquare(cpu.getFixedRow()-1, cpu.getFixedColumn()).equals("|__")){//if the square below isn't open, try to mark above
				tempRow=cpu.getAdvRow();
				cpu.setAdvRow(cpu.getFixedRow()-1);
				cpu.setFixedRow(tempRow); 	//the fixed is replaced with what advancing used to be
				cpu.setMarkedRow(cpu.getAdvRow());
				markOffBoard(cpu.getMarkedRow(),cpu.getMarkedColumn(), cpu, user.getDefBoard());
				return true;
			}else{//no empty squares on either side, abandon the strategy
				cpu.setPursuingLine(false);
				return false;
			}
		}
		private boolean findNextMovingUp(BsPlayer cpu, BsPlayer user){
			int tempRow=0;
			if(cpu.getOffBoard().getSquare(cpu.getAdvRow()-1, cpu.getFixedColumn()).equals("|__")){//if one above on the column is empty, mark that square
				cpu.setAdvRow(cpu.getAdvRow()-1);
				cpu.setMarkedRow(cpu.getAdvRow());
				markOffBoard(cpu.getMarkedRow(), cpu.getMarkedColumn(), cpu, user.getDefBoard());
				return true;
			}else if(cpu.getOffBoard().getSquare(cpu.getFixedRow()+1, cpu.getFixedColumn()).equals("|__")){//if the square below isn't open, try to mark below
				tempRow=cpu.getAdvRow();
				cpu.setAdvRow(cpu.getFixedRow()+1);
				cpu.setFixedRow(tempRow);
				cpu.setMarkedRow(cpu.getAdvRow());
				markOffBoard(cpu.getMarkedRow(), cpu.getMarkedColumn(), cpu, user.getDefBoard());
				return true;
			}else{//no empty squares on either side, abandon the strategy
				cpu.setPursuingLine(false);
				return false;
			}
		}
		private boolean findNextMovingRight(BsPlayer cpu, BsPlayer user){
			int tempCol=0;
			if(cpu.getOffBoard().getSquare(cpu.getFixedRow(), cpu.getAdvColumn()+1).equals("|__")){//if one right on the row is empty, mark that square
				cpu.setAdvColumn(cpu.getAdvColumn()+1);
				cpu.setMarkedColumn(cpu.getAdvColumn());
				markOffBoard(cpu.getMarkedRow(), cpu.getMarkedColumn(), cpu, user.getDefBoard());
				return true;
			}else if(cpu.getOffBoard().getSquare(cpu.getFixedRow(), cpu.getFixedColumn()-1).equals("|__")){//if the square right isn't open, try to mark left
				tempCol=cpu.getAdvColumn();
				cpu.setAdvColumn(cpu.getFixedColumn()-1);
				cpu.setFixedColumn(tempCol);
				cpu.setMarkedColumn(cpu.getAdvColumn());
				markOffBoard(cpu.getMarkedRow(), cpu.getMarkedColumn(), cpu, user.getDefBoard());
				return true;
			}else{//no empty squares on either side, abandon the strategy
				cpu.setPursuingLine(false);
				return false;
			}
		}
		private boolean findNextMovingLeft(BsPlayer cpu, BsPlayer user){
			int tempCol=0;
			if(cpu.getOffBoard().getSquare(cpu.getFixedRow(), cpu.getAdvColumn()-1).equals("|__")){//if one left on the row is empty, mark that square
				cpu.setAdvColumn(cpu.getAdvColumn()-1);
				cpu.setMarkedColumn(cpu.getAdvColumn());
				markOffBoard(cpu.getMarkedRow(), cpu.getMarkedColumn(), cpu, user.getDefBoard());
				return true;
			}else if(cpu.getOffBoard().getSquare(cpu.getFixedRow(), cpu.getFixedColumn()+1).equals("|__")){//if the square left isn't open, try to mark right
				tempCol=cpu.getAdvColumn();
				cpu.setAdvColumn(cpu.getFixedColumn()+1);
				cpu.setFixedColumn(tempCol);
				cpu.setMarkedColumn(cpu.getAdvColumn());
				markOffBoard(cpu.getMarkedRow(), cpu.getMarkedColumn(), cpu, user.getDefBoard());
				return true;
			}else{//no empty squares on either side, abandon the strategy
				cpu.setPursuingLine(false);
				return false;
			}
		}
		private boolean singleStrikeSurroundsOtherStrikes(BsPlayer cpu, int row, int column){
			int fails=0;
			if(!cpu.getOffBoard().getSquare(row+1, column).equals("|__")){
				fails++;
			}
			if(!cpu.getOffBoard().getSquare(row-1, column).equals("|__")){
				fails++;
			}
			if(!cpu.getOffBoard().getSquare(row, column+1).equals("|__")){
				fails++;
			}
			if(!cpu.getOffBoard().getSquare(row, column-1).equals("|__")){
				fails++;
			}
			if(fails==4){
				return true;
			}
			return false;
		}
		//a square that isn't bordering a strike or a blank square will not be a strike
		private int getBorderedMisses(BsPlayer cpu, int row, int column){
			int missBorder=0;
			if(!cpu.getOffBoard().getSquare(row+1, column).equals("|__") && !cpu.getOffBoard().getSquare(row+1, column).equals("| X")){
				missBorder++;
			}
			if(!cpu.getOffBoard().getSquare(row-1, column).equals("|__") && !cpu.getOffBoard().getSquare(row-1, column).equals("| X")){
				missBorder++;
			}
			if(!cpu.getOffBoard().getSquare(row, column+1).equals("|__") && !cpu.getOffBoard().getSquare(row, column+1).equals("| X")){
				missBorder++;
			}
			if(!cpu.getOffBoard().getSquare(row, column-1).equals("|__") && !cpu.getOffBoard().getSquare(row, column-1).equals("| X")){
				missBorder++;
			}
			return missBorder;
		}
		//returns true if a square neighboring 2 hits is marked
		private boolean fireAtTwoHitsNeighbor(BsPlayer cpu, BsPlayer user){
			int hits=0;
			for(int i=1; i<=10; i++){//search all 100 squares
				for(int j=1; j<=10; j++){
					if(cpu.getOffBoard().getSquare(i, j).equals("|__")){//if the square is available to fire at
						hits=0;			//the hits are reset to zero for every space checked
						if(cpu.getOffBoard().getSquare(i-1, j).equals("| X")){//check 1 higher for a hit
							hits++;
						}
						if(cpu.getOffBoard().getSquare(i+1, j).equals("| X")){//check 1 lower for a hit
							hits++;
						}
						if(cpu.getOffBoard().getSquare(i, j-1).equals("| X")){//check 1 to the left for a hit
							hits++;
						}
						if(cpu.getOffBoard().getSquare(i, j+1).equals("| X")){//check 1 to the right for a hit
							hits++;
						}
						if(hits>=2){										//if 2 or more hits
							markOffBoard(i, j, cpu, user.getDefBoard()); //mark the square neighboring 2 hits
							return true;
						}
					}
				}
			}
			cpu.setTwoHitNeighbor(false);
			return false;
		}	
		
}
