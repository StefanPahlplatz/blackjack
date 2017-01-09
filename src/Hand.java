import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> cards;
	private int bet;
	private boolean doubleBet;
	private boolean passed;
	private boolean dead;
	
	public Hand(int bet) {
		this.bet = bet;
		cards = new ArrayList<>();
	}
	
	public void dealCard(Card card) {
		cards.add(card);
		
		if (getTotal() > 21)
			dead = passed = true;
		if (getTotal() == 21)
			passed = true;
	}
	
	/**
	 * Returns the total of the current hand
	 * @return the current hand of the player
	 */
	public int getTotal() {
		int total = -1;
		int amountOfAces = 0;
		
		// Get the values for all cards, store the aces seperatly
		for (int i = 0; i < cards.size(); i++) {
			int cardValue = cards.get(i).getValue();
			if (cardValue == 1)
				amountOfAces++;
			else
				total += cardValue;
		}
		
		// Add the aces
		for (int i = amountOfAces; i >= 0; i--) {
			if (amountOfAces == 1 && total + 11 <= 21)
				total += 11;
			else
				total += 1;
		}
		
		return total;
	}
	
	public boolean isPassed() {
		return passed;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void pass() {
		passed = true;
	}
	
	public int betAmount() {
		return bet;
	}
	
	public void doubleBet() {
		bet *= 2;
	}
	
	public String printCards() {
		StringBuilder retString = new StringBuilder();
		
		// Add cards
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i) != null)
				retString.append(cards.get(i).toString() + " ");
		}
		
		return retString.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder retString = new StringBuilder();
		
		// Add cards
		String cardsString = "";
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i) != null)
				cardsString += (cards.get(i).toString() + " ");
		}
		retString.append(Input.padRight(cardsString, 15));
			
		
		// Add the bet amount
		retString.append("\t\tBet = " + bet);
		
		// Add whether the hand is doubled or passed
		if (getTotal() == 21)
			retString.append(" BLACKJACK!");
		else if (dead)
			retString.append(" Dead");
		else if (passed)
			retString.append(" Passed");
		else if (doubleBet)
			retString.append(" Double");
		
		return retString.toString();
	}
}
