import java.util.*;
import java.lang.*;

public class Project1 {
	
	public static void main(String[] args) {
		ArrayList<String> wordList = new ArrayList<>
			(Arrays.asList("SOFTWARE", "ENGINEER", "FULLTIME", "INTERNSHIP"));
		Hangman hangman = new Hangman(wordList);
		//System.out.println(hangman.chooseWord());
		hangman.playGame();
	}
}

class Hangman {
	private static final int MAX_GUESS = 8;
	private List<String> words;
	private String selectedWord;
	private List<Character> correctList;
	private List<Character> wrongList;

	public Hangman(ArrayList<String> words) {
		this.words = words;
		selectedWord = null;
		correctList = new ArrayList<Character>();
		wrongList = new ArrayList<Character>();
	}

	public void chooseWord() {
		selectedWord = words.get((int)(Math.random() * words.size()));
	}

	public void handleGuess() {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();

		while (input.length() != 1) {
			System.out.print("Please enter one character at a time, enter: ");
			input = sc.next();
		}

		char inputChar = input.charAt(0);

		HashSet<Character> charSet = new HashSet<>();

		for (int i = 0; i < selectedWord.length(); i++) {
			charSet.add(Character.toLowerCase(selectedWord.charAt(i)));
		} 


		if (charSet.contains(inputChar)) {
			//System.out.println();
			charSet.remove(inputChar);
			correctList.add(inputChar);
		}
	}

	public boolean gameWon() {

		HashSet<Character> charSet = new HashSet<>();

		for (int i = 0; i < selectedWord.length(); i++) {
			charSet.add(Character.toLowerCase(selectedWord.charAt(i)));
		} 

		for (Character c : correctList) {
			if (!charSet.contains(c)) return false;
		}
		
		return true; 
	}

	public void gameOver() {
		if (wrongList.size() >= MAX_GUESS) {
			System.out.println("Sorry, you lost!");
		} 
		else if (gameWon()) {
			System.out.println("Congratulations, You won!");
		}

		System.exit(0);
	}

	public void printHangman() {
		System.out.print(" ");
		displayWord();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 11; j++) {
				if (j == 0) System.out.print("|");
			}
		}
		/*System.out.println("|" + String.format("%" + 11 + "s", "") + "|");

		System.out.print("|" + String.format("%" + 11 + "s", ""));
		if (wrongList.size() > 0) {
			System.out.println("0");
		} 
		
		System.out.println();
		System.out.print("|" + String.format("%" + 11 + "s", ""));
		if (wrongList.size() > 1) {
			System.out.println("|");
		}

		System.out.println();
		System.out.print("|" + String.format("%" + 7 + "s", ""));
		if (wrongList.size() > 2) {
			System.out.print("--- ");
		}
		if (wrongList.size() > 3) {
			System.out.println("---");
		}

		System.out.println();
		System.out.print("|" + String.format("%" + 7 + "s", ""));*/
	}

	public void playGame() {
		chooseWord();
		System.out.println("Welcome to Hangman!");
		System.out.println();
		System.out.println("You have maximum " + MAX_GUESS + " times to make mistakes.");

		System.out.println("Please enter a character (upper or lower), then hit 'ENTER': ");
		
		//handleGuess();
		printHangman();
	}

	public void displayWord() {
		char[] status = new char[selectedWord.length()];
		Arrays.fill(status, '-');

		for (Character c : correctList) {
			for (int i = 0; i < selectedWord.length(); i++) {
				if (Character.toLowerCase(selectedWord.charAt(i)) == c)
					status[i] = c;
			}
		}
		for (int i = 0; i < status.length; i++) {
			System.out.print(status[i]);
		}
		System.out.println();
	}
	
}