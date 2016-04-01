package BJmodel;



public class Card {
	private SuitEnum suit;
	private RankEnum rank;
	private String fileName;
	
	public Card(SuitEnum suit, RankEnum rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public void setRankEnum(RankEnum rank) {
		this.rank = rank;
	}
	
	public RankEnum getRankEnum() {
		return rank;
	}
	
	public void setSuit(SuitEnum suit) {
		this.suit = suit;
	}
	
	public SuitEnum getSuit() {
		return suit;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
