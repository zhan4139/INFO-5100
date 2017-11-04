package GameOfHearts;

public class Card {
	private int num;
	private int suit;

	public Card(int num, int suit) {
		this.num = num;
		this.suit = suit;
	}

	public void display() {
		String numString, suitString;

		switch (num) {
		case (11):
			numString = "Jack";
			break;
		case (12):
			numString = "Queen";
			break;
		case (13):
			numString = "King";
			break;
		case (14):
			numString = "Ace";
			break;
		default:
			numString = String.valueOf(num);
			break;
		}

		switch (suit) {
		case (0):
			suitString = "Clubs";
			break;
		case (1):
			suitString = "Diamonds";
			break;
		case (2):
			suitString = "Hearts";
			break;
		case (3):
			suitString = "Spades";
			break;
		default:
			suitString = "";
			break;
		}

		System.out.println(numString + " of " + suitString);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}
}
