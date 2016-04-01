package BJmodel;

import java.util.ArrayList;
import java.util.Collections;
//part of the Model
public class Deck {
	
	public ArrayList<Card> deckOfCards = new ArrayList<Card>(52);
	 
	public Deck() {
		RankEnum[] rank = RankEnum.values();
		SuitEnum[] suit = SuitEnum.values();
		for(int i = 0; i < suit.length; i++) {
			for(int j = 0; j < rank.length; j++) {
				Card card = new Card(suit[i], rank[j] );
				getFile(card);
				deckOfCards.add(card);
				
				
			}
		}
	}//end Deck
	
	public void shuffle() {
		Collections.shuffle(deckOfCards);
		
	}
	
	public void deal(ArrayList<Card>hand, int numOfCards) {
		for(int i = 0; i < numOfCards; i++) {
			hand.add(deckOfCards.get(i));
			this.deckOfCards.remove(i);
		}
	}
	
	public void clearHand(Player p) {
		for(int i = p.getPlayerHand().size() - 1; i >= 0; i--) {
			deckOfCards.add(p.getPlayerHand().get(i));
			p.getPlayerHand().remove(i);
		}
	}
	
	
	public String suitLetter(SuitEnum num) {
		switch(num) {
		case DIAMOND:
			return "d";
		case HEARTS:
			return "h";
		case SPADES:
			return "s";
		case CLUBS:
			return "c";
		}
		return null;
	}
	
	public void getFile(Card c) {
		
		String fileName = suitLetter(c.getSuit()) + c.getRankEnum().getRankValue() + ".bmp";
		
		c.setFileName("./BJimages/card/" + fileName);
		
		
	}
	
	
}//end class
