package GameOfHearts;

public class Hand extends GroupOfCards {

	public final int NUM;
	private int shortest = 0;

	public Hand(int playerNum, int numberOfCards) {
		super(numberOfCards);
		this.NUM = playerNum;
	}

	public void sort() {// selection sort
		if (getCurrentSize() == 0)
			return;
		int n = getCurrentSize();
		for (int i = 0; i < n - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < n; j++) {
				int num = getCard(j).getSuit() * 13 + getCard(j).getNum();
				int num2 = getCard(minIdx).getSuit() * 13 + getCard(minIdx).getNum();
				if (num < num2)
					minIdx = j;
			}

			addCard(removeCard(minIdx));
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
		setShortest();
		if (trick.getCurrentSize() == 0 && (index = findHighest(shortest)) >= 0) {

			// System.out.println("shortest 1: " + shortest + "index: " + index);

		} else if (trick.getCurrentSize() == game.PLAYERS - 1 && !trick.getHearts() && !trick.getQueen()
				&& (index = findHighest(shortest)) >= 0) {
			// System.out.println("shortest 2: " + shortest + " index: " + index);
		} else if (trick.getWinningCard() != null && (index = findHighestBelow(trick.getWinningCard())) >= 0) {
			// System.out.println("findHighestBelow - index: " + index);
		} else if (trick.getWinningCard() != null
				&& (index = findMiddleHigh(game, trick.getWinningCard().getSuit())) >= 0) {
			// System.out.println("findMiddleHigh - index: " + index);
		} else if ((index = find(12, 3)) >= 0)
			; // queen of Spades
		else if ((index = find(14, 3)) >= 0)
			; // Ace of Spades
		else if ((index = find(13, 3)) >= 0)
			; // King of Spades
		else if ((index = findHighest(2)) >= 0)
			; // heart
		else {
			index = findHighest();
			// System.out.println("findHighest() - index: " + index);

		}
		Card giveout = removeCard(index);
		trick.update(NUM, giveout);
		game.updateHeartsAndQueen(giveout);

		return giveout;

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
		int highest = findHighest(winningCard.getSuit());
		int index = find(highest, winningCard.getSuit());
		if (index < 0)
			return index;
		while (index >= 0 && highest > winningCard.getNum()) {
			index--;
			highest = getCard(index).getNum();
			if (getCard(index).getSuit() != winningCard.getSuit())
				return -1;
		}
		return index;
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
