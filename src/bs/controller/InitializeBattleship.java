package bs.controller;

import bsmodel.BsGame;

public class InitializeBattleship {

	BsGame game;
	
	public InitializeBattleship(BsGame game, String turn){
		this.game=game;
		setAllBlankBoards();	//makes the blank string arrays for back end logic
		initializeJSPInfo();	//makes the blank board integer codes for jsp
		game.getUser().getDefBoard().setAmountShipsOnBoard(0);
		game.getCPU().getDefBoard().setAmountShipsOnBoard(0);
		setFirstPlayerTurn(turn);
	}
	
	private void setFirstPlayerTurn(String turn){
		if(turn.equals("before")){
			game.getUser().setTurnToShoot(true);
			game.getUser().setGoesFirst(true);		//important for Missile Handler  loop
		}else{
			game.getCPU().setTurnToShoot(true);
			game.getCPU().setGoesFirst(true);
		}
	}
	/* String array board creation for back end. This method is called in the constructor */
	//accessing a square on all 4 boards at once
	private void setAllBoardSquares(int row, int column, String value){
		game.getUser().getDefBoard().setSquare(row, column, value);
		game.getCPU().getDefBoard().setSquare(row, column, value);
		game.getUser().getOffBoard().setSquare(row, column, value);
		game.getCPU().getOffBoard().setSquare(row, column, value);
	}
	// setting the A-J and 1-10 labels on the side of the String array on all 4 boards
	private void setBoardSideLabels() {
		setAllBoardSquares(0, 0, " ");	//upper left square is blank
		for (int i = 1; i < 11; i++) {
			setAllBoardSquares(i, 0, String.valueOf(Character.toChars(i + 64)));
			setAllBoardSquares(0, i, String.valueOf(" " + i + " "));
		}
	}
	//makes all blank double String array for the start of a game
	private void setAllBlankBoards(){
		setBoardSideLabels();
		for(int i=1; i<game.getUser().getDefBoard().getRows(); i++){
			for(int j=1; j<game.getUser().getDefBoard().getColumns(); j++){
				setAllBoardSquares(i, j, "|__");
			}
		}
	}

	// 20 -> water image
	// 21 -> end face north
	// 22 -> end face east
	// 23 -> end face south
	// 24 -> end face west
	// 25 -> horizontal
	private void initializeJSPInfo() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				game.getUser().setJspInfo(i, j, 20);	//the defense board (the board is all water images at the beginning)
				game.getCPU().setJspInfo(i, j, 50); //the firing board (everything is a blue button in the beginning)
			}
		}
		game.getUser().setJspInfo(0, 0, -1); //the corner space in the upper left should not be water image
	} 
}
