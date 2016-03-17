package bsmodel;

public class BsPlayer {
	
	private String name = "You";
	//the defensive board has ships and the other player's guessed strikes
	//the offensive board has the player's strikes but no ships
	BsBoard defBoard, offBoard;
	//the view will show the user's offensive and defensive boards
	
	private int shipSpacesToGet = 17;
	private boolean hasWon=false;
	private boolean goesFirst=false;
	private boolean turnToShoot = false;
	private boolean alreadyFiredThere = false;
	private boolean lineAfterMiss=false;
	private int numberOfShots = 0;
	private int rowFromLastTime;
	private int columnFromLastTime;
	private boolean lastShotWasHit;
	
	//AI variables
	private boolean pursuingLine=false, singleStrikeWasHit=false, twoHitNeighbor=false;
	private int randSearchFails=0, searchStandard=15, fixedRow, fixedColumn, advRow, advColumn;
	private int markedRow, markedColumn;
	
	private int[][] jspInfo = new int[11][11];
	
	public BsPlayer(){
		defBoard=new BsBoard();
		offBoard=new BsBoard();
	}
	public int[][] getJspInfo(){
		return jspInfo;
	}
	public int getJSPInfoSpace(int row, int column){
		return jspInfo[row][column];
	}
	public void setJspInfo(int a, int b, int c){
		try{
			jspInfo[a][b] = c;
		}catch(ArrayIndexOutOfBoundsException exc){
			
		}
	}
	
	public BsBoard getDefBoard(){
		return defBoard;
	}
	public BsBoard getOffBoard(){
		return offBoard;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	// get ship spaces to see is anyone won the game
	public int getShipSpacesLeftToGet() {
		return shipSpacesToGet;
	}

	// set the ship spaces to be 1 lower
	public void decreaseShipSpaces() {
		shipSpacesToGet--;
	}
	
	public void setWinStatus(boolean hasWon){
		this.hasWon=hasWon;
	}
	public boolean getWinStatus(){
		return hasWon;
	}
	public boolean isTurnToShoot() {
		return turnToShoot;
	}
	public void setTurnToShoot(boolean turnToShoot) {
		this.turnToShoot = turnToShoot;
	}
	public boolean isAlreadyFiredThere() {
		return alreadyFiredThere;
	}
	public void setAlreadyFiredThere(boolean alreadyFiredThere) {
		this.alreadyFiredThere = alreadyFiredThere;
	}
	public int getNumberOfShots() {
		return numberOfShots;
	}
	public void setNumberOfShots(int numberOfShots) {
		this.numberOfShots = numberOfShots;
	}
	public boolean isGoesFirst() {
		return goesFirst;
	}
	public void setGoesFirst(boolean goesFirst) {
		this.goesFirst = goesFirst;
	}
	
	//AI variable getters and setters
	public boolean isPursuingLine() {
		return pursuingLine;
	}
	public void setPursuingLine(boolean pursuingLine) {
		this.pursuingLine = pursuingLine;
	}
	public boolean isSingleStrikeWasHit() {
		return singleStrikeWasHit;
	}
	public void setSingleStrikeWasHit(boolean singleStrikeWasHit) {
		this.singleStrikeWasHit = singleStrikeWasHit;
	}
	public boolean isTwoHitNeighbor() {
		return twoHitNeighbor;
	}
	public void setTwoHitNeighbor(boolean twoHitNeighbor) {
		this.twoHitNeighbor = twoHitNeighbor;
	}
	public int getRandSearchFails() {
		return randSearchFails;
	}
	public void setRandSearchFails(int randSearchFails) {
		this.randSearchFails = randSearchFails;
	}
	public int getSearchStandard() {
		return searchStandard;
	}
	public void setSearchStandard(int searchStandard) {
		this.searchStandard = searchStandard;
	}
	public int getFixedRow() {
		return fixedRow;
	}
	public void setFixedRow(int fixedRow) {
		this.fixedRow = fixedRow;
	}
	public int getFixedColumn() {
		return fixedColumn;
	}
	public void setFixedColumn(int fixedColumn) {
		this.fixedColumn = fixedColumn;
	}
	public int getAdvRow() {
		return advRow;
	}
	public void setAdvRow(int advRow) {
		this.advRow = advRow;
	}
	public int getAdvColumn() {
		return advColumn;
	}
	public void setAdvColumn(int advColumn) {
		this.advColumn = advColumn;
	}
	public int getMarkedRow() {
		return markedRow;
	}
	public void setMarkedRow(int markedRow) {
		this.markedRow = markedRow;
	}
	public int getMarkedColumn() {
		return markedColumn;
	}
	public void setMarkedColumn(int markedColumn) {
		this.markedColumn = markedColumn;
	}
	public boolean isLineAfterMiss() {
		return lineAfterMiss;
	}
	public void setLineAfterMiss(boolean lineAfterMiss) {
		this.lineAfterMiss = lineAfterMiss;
	}
	public int getRowFromLastTime() {
		return rowFromLastTime;
	}
	public void setRowFromLastTime(int rowFromLastTime) {
		this.rowFromLastTime = rowFromLastTime;
	}
	public int getColumnFromLastTime() {
		return columnFromLastTime;
	}
	public void setColumnFromLastTime(int columnFromLastTime) {
		this.columnFromLastTime = columnFromLastTime;
	}
	public boolean isLastShotWasHit() {
		return lastShotWasHit;
	}
	public void setLastShotWasHit(boolean lastShotWasHit) {
		this.lastShotWasHit = lastShotWasHit;
	}
	

}
