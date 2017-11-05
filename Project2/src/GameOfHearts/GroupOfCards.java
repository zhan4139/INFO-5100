package GameOfHearts;

public class GroupOfCards {
	private Card[] cards;
	private int currentSize;

	public GroupOfCards(int num) {
		cards = new Card[num];
	}

	public void addCard(Card card) {
		if (currentSize < cards.length) {
			cards[currentSize] = card;
			currentSize++;
		}

	}

	public Card removeCard(int index) {
		Card card = this.getCard(index);
		for (int i = index; i < this.getCurrentSize() - 1; i++) {
			this.cards[i] = this.cards[i + 1];
		}
		this.setCurrentSize(this.getCurrentSize() - 1);
		return card;

	}

	public void display() {
		for (int i = 0; i < currentSize; i++) {
			cards[i].display();
		}
	}

	public Card getCard(int i) {
		return cards[i];
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(int currentSize) {
		this.currentSize = currentSize;
	}

}
