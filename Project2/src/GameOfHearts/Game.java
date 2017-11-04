package GameOfHearts;

public class Game {
	public final int PLAYERS;
	private Deck deck;
	private Hand[] players;
	private Trick[] tricks;
	private int numberOfTricks;
	private boolean hearts;
	private boolean queenOfSpades;

	public Game(int numberOfPlayers) {
		PLAYERS = numberOfPlayers;
		deck = new Deck();
		players = new Hand[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++) {
			players[i] = new Hand(i, 52 / numberOfPlayers);
		}
		tricks = new Trick[52 / numberOfPlayers];

	}

	public int getNumberOfTricks() {
		return numberOfTricks;
	}

	public boolean getHearts() {
		return hearts;
	}

	public boolean getQueenOfSpades() {
		return queenOfSpades;
	}

	public void playAGame() {
		deck.shuffle();

		for (int i = 0; i < 52 / PLAYERS; i++) {
			for (int j = 0; j < PLAYERS; j++) {
				Card nextCard = deck.dealCard();
				players[j].addCard(nextCard);
			}
		}

		for (int i = 0; i < players.length; i++) {
			players[i].sort();
			players[i].setShortest();
		}

		// First Part
		int minNum = Integer.MAX_VALUE;
		int playerNum = 0;
		for (int i = 0; i < PLAYERS; i++) {
			System.out.println("Player " + i + " shortest = " + players[i].getShortest());
			for (int j = 0; j < players[i].getCurrentSize(); j++) {
				players[i].getCard(j).display();
				if (players[i].getCard(j).getSuit() == 0) {
					if (players[i].getCard(j).getNum() < minNum) {
						playerNum = i;
						minNum = players[i].getCard(j).getNum();
					}
				}
			}
		}

		// Second Part
		int left = 52 % PLAYERS;
		for (int i = 0; i < tricks.length; i++) {
			tricks[i] = new Trick(PLAYERS);

			for (int j = 0, pn = playerNum; j < PLAYERS; j++, pn++) {
				Card card;
				pn = pn % PLAYERS;
				if (i == 0 && j == 0) {
					card = players[pn].removeCard(0);
				} else {
					card = players[pn].playACard(this, tricks[i]);
				}
				tricks[i].addCard(card);
				tricks[i].update(pn, card);
				updateHeartsAndQueen(card);
				System.out.print("Player " + pn + "      ");
				card.display();
			}

			if (i == 0) {
				for (int j = 0; j < left; j++) {
					Card card = deck.dealCard();
					tricks[i].addCard(card);
					updateHeartsAndQueen(card);
					System.out.print("Undelt Card " + playerNum + "     ");
					card.display();
				}
			}
			playerNum = tricks[i].getWinner();
			System.out.println();
		}
		//score
		for (int i = 0; i < PLAYERS; i++) {
			System.out.println("Player " + i + ": score = " + computePoints(i));
		}
	}

	public void updateHeartsAndQueen(Card card) {
		if (card.getSuit() == 2 && !hearts) {
			System.out.println("Hearts is now broken");
			hearts = true;
			return;
		}

		if (card.getSuit() == 3 && card.getNum() == 12) {
			queenOfSpades = true;
			return;
		}
	}

	private int computePoints(int playerNum) {
		int total = 0;

		for (int i = 0; i < tricks.length; i++) {
			if (tricks[i].getWinner() == playerNum) {
				for (int j = 0; j < tricks[i].getCurrentSize(); j++) {
					Card card = tricks[i].getCard(j);
					if (card.getSuit() == 2)
						total++;
					if (card.getSuit() == 3 && card.getNum() == 12)
						total += 13;
				}
			}
		}

		return total;
	}
}
