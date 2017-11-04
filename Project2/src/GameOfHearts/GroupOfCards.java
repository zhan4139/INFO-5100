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
		if (index <= currentSize) {
			Card removed = cards[index];
			for (int i = index; i > 0; i--) {
				cards[i] = cards[i - 1];
			}
			cards[currentSize - 1] = null;
			currentSize--;
			return removed;
		}
		return null;

	}

	public void display() {
		for (int i = 0; i < currentSize; i++) {
			cards[i].display();
		}
	}

	public Card getCard(int i) {
		return cards[i];
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(int currentSize) {
		this.currentSize = currentSize;
	}

}
