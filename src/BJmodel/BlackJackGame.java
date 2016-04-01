package BJmodel;

public class BlackJackGame {

	private Deck deck;
	private Player p1;
	private Player dealer;
	private int isBet;
	private boolean isDealerTurn;
	private int betPot;
	
	public BlackJackGame() {
		
	}
	
	public BlackJackGame(int chipCount) {
		
		 setDeck(new Deck());
		 setP1(new Player("Steve", chipCount));
		 setDealer(new Player("Dealer", 100000000));
	}

	public int isBet() {
		return isBet;
	}

	public void setBet(int isBet) {
		this.isBet = isBet;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getDealer() {
		return dealer;
	}

	public void setDealer(Player dealer) {
		this.dealer = dealer;
	}

	public boolean isDealerTurn() {
		return isDealerTurn;
	}

	public void setDealerTurn(boolean isDealerTurn) {
		this.isDealerTurn = isDealerTurn;
	}

	public int getBetPot() {
		return betPot;
	}

	public void setBetPot(int betPot) {
		this.betPot = betPot;
	}
}
