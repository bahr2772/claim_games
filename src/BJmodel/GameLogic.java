package BJmodel;



public class GameLogic {	

	public BlackJackGame useDecision(String choice, BlackJackGame bjGame) {

		if(choice.equals("H")) {
			hit(bjGame);
			bjGame.getP1().setCurrentHandCount(countHand(bjGame.getP1()));
		}
		else {
			bjGame.setDealerTurn(true);

			while(countHand(bjGame.getDealer()) < 17) {
				
					dealerHit(bjGame);
					bjGame.getDealer().setCurrentHandCount(countHand(bjGame.getDealer()));
				
				
			}
			winner(bjGame);
			
		}
		return bjGame;
	}

	public BlackJackGame useBets(int betAmount, BlackJackGame bjGame) {
		
		bjGame.setBetPot(betAmount);
		bjGame.getP1().setChipCount(bjGame.getP1().getChipCount() - betAmount);
		initialDeal(bjGame);
		return bjGame;
	}

	public void hit(BlackJackGame game) {
		game.getDeck().deal(game.getP1().getPlayerHand(), 1);

	}

	public void dealerHit(BlackJackGame game) {
		
		game.getDeck().deal(game.getDealer().getPlayerHand(), 1);
	}

	public int countHand(Player p) {
		int count = 0;
		int aceCount = 0;
		for(int i = 0; i < p.getPlayerHand().size(); i++) {
			switch(p.getPlayerHand().get(i).getRankEnum().getRankValue()) {
			case 11:
				count += 10;
				break;
			case 12:
				count += 10;
				break;
			case 13:
				count += 10;
				break;
			case 14:
				count += 11;
				aceCount++;
				break;
			default: 
				count += p.getPlayerHand().get(i).getRankEnum().getRankValue();
			}
		}
		if(count > 21)
			for(int i = 0; i < aceCount; i++) {
				count-= 10;
				if(count <= 21)
					break;
			}
		
		return count;
	}

	public BlackJackGame initialDeal(BlackJackGame game) {

		
		
		game.getDeck();
		game.getDeck().shuffle();
		
		game.getDeck().clearHand(game.getP1());
		game.getDeck().clearHand(game.getDealer());
	
		
		game.getP1().setCurrentHandCount(0);
		game.getDeck().deal(game.getP1().getPlayerHand(), 2);
		game.getP1().setCurrentHandCount(countHand(game.getP1()));
		

		game.getDealer().setCurrentHandCount(0);
		game.getDeck().deal(game.getDealer().getPlayerHand(), 2);
		
		game.getDealer().setCurrentHandCount(countHand(game.getDealer()));
		
		return game;
	}

		//Determines the winner of the hand and displays their chip count.
	public void winner(BlackJackGame game) {
		if(!game.getP1().bust() && (game.getDealer().bust() || game.getP1().getCurrentHandCount() > game.getDealer().getCurrentHandCount()))	{
			//setChips here
			game.getP1().setChipCount(game.getP1().getChipCount() + game.getBetPot() * 2);
			//System.out.println("You Wins you now have " + game.getP1().getChipCount() + " chips.");
		}
		else if((!game.getP1().bust() && !game.getDealer().bust()) && (game.getP1().getCurrentHandCount() == game.getDealer().getCurrentHandCount())) {
			//get chips that you bet back
			game.getP1().setChipCount(game.getP1().getChipCount() + game.getBetPot());
			//System.out.println("It's a draw you have " + game.getP1().getChipCount() + " chips.");
		}
		else {
			//System.out.println("You lose, you have " + game.getP1().getChipCount() + " chips.");
		}

		game.setBetPot(0);
		game.setBet(3);
		
	}

	public BlackJackGame playAgain(String yN, BlackJackGame bjGame) {
		if(yN.equals("Y")){
			bjGame.setBet(1);
		}
	
		return bjGame;
	}
	
	
}
