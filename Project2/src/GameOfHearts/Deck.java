package GameOfHearts;

public class Deck extends GroupOfCards {
	public static final int TOTAL_CARDS = 52;

	public Deck() {
		super(TOTAL_CARDS);
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 15; j++) {
				Card card = new Card(j, i);
				super.addCard(card);
			}
		}
	}

	public void shuffle() {
		int unshuffled = this.getCurrentSize();
		while (unshuffled > 0) {
			int index = (int) (Math.random() * unshuffled);
			Card card = this.removeCard(index);
			this.addCard(card);
			unshuffled--;
		}
	}

	public Card dealCard() {
		return super.removeCard(0);
	}
}
