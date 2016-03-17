package bsmodel;
public class BsBoard{
	
	final int ROWS=11;
	final int COLUMNS=11;
	private String[][] grid = new String[ROWS][COLUMNS];
	private int shipsOnBoard = 0; 
	private int currentShipLength = 5;
	private boolean placedSize3 = false;
	
	private boolean offOfBoard=false;
	private boolean overlap=false;
	private int shotsFired=0;
	private int hits=0;
	
	private int[][] jspInfo = new int[11][11];
	
	public int getRows(){
		return ROWS;
	}
	public int getColumns(){
		return COLUMNS;
	}
	//set an individual square on the board's grid
	public void setSquare(int row, int column, String value){
			grid[row][column] = value;
		
	}
	//get an individual square from the grid String array
	public String getSquare(int row, int column){
		try{
			return grid[row][column];
		}catch(ArrayIndexOutOfBoundsException exc){
			offOfBoard=true;
		}catch(NullPointerException exc){
			offOfBoard=true;
		}
		return "hi";
	}
	//get the game board's String array
	public String[][] getGrid(){
		return grid;
	}

	public int getShipsOnBoard() {
		return shipsOnBoard;
	}

	public void setAmountShipsOnBoard(int shipsOnBoard) {
		this.shipsOnBoard = shipsOnBoard;
	}

	public int getCurrentShipLength() {
		return currentShipLength;
	}

	public void setCurrentShipLength(int currentShipLength) {
		this.currentShipLength = currentShipLength;
	}

	public boolean hasPlacedSize3() {
		return placedSize3;
	}

	public void setPlacedSize3(boolean placedSize3) {
		this.placedSize3 = placedSize3;
	}
	
	public boolean isOffOfBoard() {
		return offOfBoard;
	}
	public void setOffOfBoard(boolean offOfBoard) {
		this.offOfBoard = offOfBoard;
	}
	public boolean isOverlap() {
		return overlap;
	}
	public void setOverlap(boolean overlap) {
		this.overlap = overlap;
	}
	public int getShotsFired() {
		return shotsFired;
	}
	public void setShotsFired(int shotsFired) {
		this.shotsFired = shotsFired;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int[][] getJspInfo() {
		return jspInfo;
	}
	public void setJspInfo(int[][] jspInfo) {
		this.jspInfo = jspInfo;
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
}