package GameOfHearts;

public class Trick extends GroupOfCards {
	private int winner;
	private Card winningCard;
	private boolean hearts;
	private boolean queen;

	public Trick(int players) {
		super(players * 2 - 1);
	}

	public void update(int playerNum, Card card) {
		if (isWinner(card)) {
			setWinningCard(card);
			winner = playerNum;
		}
		
		if (card.getSuit() == 2) {
			hearts = true;
		}
		
		if (card.getNum() == 12 && card.getSuit() == 3) {
			queen = true;
		}
	}

	public boolean isWinner(Card card) {
		if (winningCard == null) return true;
		if (winningCard.getSuit() == card.getSuit()) {
			if(winningCard.getNum() < card.getNum()) {
				return true;
			} else {
				return false;
			}
		} 
		return false;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public Card getWinningCard() {
		return winningCard;
	}

	public void setWinningCard(Card winningCard) {
		this.winningCard = winningCard;
	}

	public boolean getHearts() {
		return hearts;
	}

	public void setHearts(boolean hearts) {
		this.hearts = hearts;
	}

	public boolean getQueen() {
		return queen;
	}

	public void setQueen(boolean queen) {
		this.queen = queen;
	}

}
