package GameOfHearts;

public class Hand extends GroupOfCards {

	public final int NUM;
	private int shortest = 0;

	public Hand(int playerNum, int numberOfCards) {
		super(numberOfCards);
		this.NUM = playerNum;
	}

	public void sort() {// selection sort
		int n = getCurrentSize();
		for (int i = n; i > 0; i--) {
			int idx = i - 1;
			for (int j = 0; j < i; j++) {
				int num = getCard(j).getSuit() * 13 + getCard(j).getNum();
				int num2 = getCard(idx).getSuit() * 13 + getCard(idx).getNum();
				if (num > num2)
					idx = j;
			}

			addCard(removeCard(idx));
		}

	}

	public int getShortest() {
		return shortest;
	}

	public void setShortest() {
		int clubCount = 0;
		int diamondCount = 0;
		int heartCount = 0;
		int spadeCount = 0;
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i).getSuit() == 0) {
				clubCount++;
			} else if (getCard(i).getSuit() == 1) {
				diamondCount++;
			} else if (getCard(i).getSuit() == 2) {
				heartCount++;
			} else {
				spadeCount++;
			}
		}

		shortest = 0;

		if ((diamondCount <= clubCount || clubCount == 0)) {
			shortest = 1;
			if ((spadeCount <= diamondCount || diamondCount == 0)) {
				if (find(12, 3) != -1 && find(13, 3) != -1 && find(14, 3) != -1) {
					shortest = 3;
					if (spadeCount == 0) {
						shortest = 2;
					}
				}
			}
		}
	}

	public Card playACard(Game game, Trick trick) {
		int index = -1;
		this.setShortest();
		if (trick.getCurrentSize() == 0) {
			int suit = this.getShortest();
			if (suit == 2) {
				index = this.findLowest(2);
			} else {
				index = this.findHighest(suit);
			}
		} else if (trick.getCurrentSize() == game.PLAYERS - 1 && !trick.getHearts() && !trick.getQueen()) {
			int suit = trick.getCard(0).getSuit();
			index = this.findLastHigh(suit);
		} else {
			if (findHighestBelow(trick.getWinningCard()) >= 0) {
				index = findHighestBelow(trick.getWinningCard());
			} else {
				int suit = trick.getCard(0).getSuit();
				index = findMiddleHigh(game, suit);
			}
		}
		if (index < 0) {
			if ((index = find(12, 3)) >= 0)
				; // queen of Spades
			else if ((index = find(14, 3)) >= 0)
				; // Ace of Spades
			else if ((index = find(13, 3)) >= 0)
				; // King of Spades
			else if ((index = findHighest(2)) >= 0)
				; // heart
			else {
				index = findHighest();
			}
		}
		Card card = this.removeCard(index);
		trick.update(this.NUM, card);
		return card;
	}

	public int findLowest(int suit) {
		if (findCount(suit) == 0)
			return -1;

		int lowest = Integer.MAX_VALUE;
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i).getSuit() == suit) {
				lowest = Math.min(lowest, getCard(i).getNum());
			}
		}
		return lowest;
	}

	private int findCount(int suit) {
		int count = 0;
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i).getSuit() == suit) {
				count++;
			}
		}
		return count;
	}

	private int find(int num, int suit) {
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i).getNum() == num && getCard(i).getSuit() == suit) {
				return i;
			}
		}
		return -1;
	}

	private int findHighest(int suit) {
		if (findCount(suit) == 0)
			return -1;

		int highest = 0;
		int index = -1;
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i).getSuit() == suit) {
				highest = Math.max(highest, getCard(i).getNum());
				index = i;
			}
		}
		return index;
	}

	private int findLowest(Game game) {
		int lowest = Integer.MAX_VALUE;
		for (int i = 0; i < getCurrentSize(); i++) {
			Card card = getCard(i);
			if (game.getHearts() && card.getNum() < lowest) {
				lowest = card.getNum();
			} else if (card.getSuit() != 2 && card.getNum() < lowest) {
				lowest = card.getNum();
			} else {
			}
		}
		return lowest;
	}

	private int findLastHigh(int suit) {
		int highest = findHighest(suit);
		if (highest == -1)
			return -1;
		if (suit == 3 && getCard(highest).getNum() == 12) {
			int index = find(12, 3);
			if (getCard(index - 1).getSuit() == suit)
				return index - 1;
			else
				return highest;
		}
		return highest;
	}

	private int findHighestBelow(Card winningCard) {
		for (int i = 0; i < getCurrentSize(); ++i) {
			Card card = getCard(i);
			if (card.getSuit() == winningCard.getSuit() && card.getNum() < winningCard.getNum()) {
				if (i != getCurrentSize() - 1 && getCard(i + 1).getSuit() != card.getSuit())
					break;
				return i;
			}
		}

		return -1;
	}

	private int findMiddleHigh(Game game, int suit) {
		if (suit != 3 || game.getQueenOfSpades()) {
			return findHighest(suit);
		} else {
			return find(findHighest(suit), suit);
		}
	}

	private int findHighest() {
		int index = -1;
		int max = 0;
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i).getNum() > max) {
				max = getCard(i).getNum();
				index = i;
			}
		}
		return index;
	}
}
