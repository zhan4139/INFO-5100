import java.util.*;
import java.lang.*;

public class Project1 {
	
	public static void main(String[] args) {
		ArrayList<String> wordList = new ArrayList<>
			(Arrays.asList("SOFTWARE", "ENGINEER", "FULLTIME", "INTERNSHIP"));
		Hangman hangman = new Hangman(wordList);
		hangman.playGame();
	}

}

class Hangman {
	private static final int MAX_GUESS = 8;
	private List<String> words;//list of words that can be selected
	private String selectedWord;//selected one
	private List<Character> correctList;//list for storing correct characters in lowercase
	private List<Character> wrongList;////list for storing wrong characters in lowercase

	//constructor, here we think the list's default size and each word's size is greather than 0, 
	//	otherwise it doesn't make sense
	public Hangman(ArrayList<String> words) {
		this.words = words;
		selectedWord = null;
		correctList = new ArrayList<Character>();
		wrongList = new ArrayList<Character>();
	}

	//using random number from 0 to list's size - 1 to select the word
	public void chooseWord() {
		selectedWord = words.get((int)(Math.random() * words.size()));
	}

	//1. Handle system.in, the input character can be upper case or lower case,
	//		I will automatically change it to lower case using toLowerCase(), but the length should be 1
	//2. I used a hashset for storing selected word's characters, to make sure each valid guess will be
	//		used only once. 
	//3. Add correct character into correctList and wrong one to wrongList, make sure when add to correctList
	//		there is no same one already in the list
	public void handleGuess() {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();

		while (input.length() != 1) {
			System.out.print("Please enter one character at a time, enter: ");
			input = sc.next();
		}

		char inputChar = Character.toLowerCase(input.charAt(0)); //change all to lower cases

		HashSet<Character> charSet = new HashSet<>();

		for (int i = 0; i < selectedWord.length(); i++) {
			charSet.add(Character.toLowerCase(selectedWord.charAt(i)));
		} 

		System.out.print("\033[H\033[2J");
		System.out.flush();

		if (charSet.contains(inputChar) && !correctList.contains(inputChar)) {
			System.out.println("You entered a correct character, keep going!");
			charSet.remove(inputChar);
			correctList.add(inputChar);
		} else {
			System.out.println("You entered a wrong character, you still have " 
				+ (MAX_GUESS - 1 - wrongList.size()) + " chances to make mistakes.");
			wrongList.add(inputChar);
		}
	}

	//Use hashSet to record selected word's characters. If all character in correctList
	//	is also in hashSet, and they have same size, then we won the game, otherwise we haven't 
	public boolean gameWon() {

		HashSet<Character> charSet = new HashSet<>();

		for (int i = 0; i < selectedWord.length(); i++) {
			charSet.add(Character.toLowerCase(selectedWord.charAt(i)));
		} 

		if (correctList.size() == 0) return false;
		for (Character c : correctList) {
			if (!charSet.contains(c)) return false;
		}
		
		return correctList.size() == charSet.size(); 
	}

	//Check whether we won the game or the wrongList hit the MAX_GUESS limit, then print corresponding
	//	message and exit.
	public void gameOver() {
		if (wrongList.size() >= MAX_GUESS) {
			System.out.println("Sorry, you lost!");
		} 
		else if (gameWon()) {
			System.out.println("Congratulations, You won!");
		}

		System.exit(0);
	}

	//Use 2d char array to print games process, note that we hardcode the size but it can be
	//	adjusted according to further requirements
	public void printHangman() {
		System.out.print(" ");
		displayWord();
		char[][] array = new char[8][11]; 
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 11; j++) {
				array[i][j] = ' ';
				if (j == 0) array[i][j] = '|';
				if (i == 0 && j == 6) array[i][j] = '|';
				if (i == 1 && j == 6 && wrongList.size() >= 1) array[i][j] = '0'; 
				if (i == 2 && j == 6 && wrongList.size() >= 2) array[i][j] = '|';
				if (i == 3 && j >= 3 && j < 6 && wrongList.size() >= 3) array[i][j] = '-';
				if (i == 3 && j >= 7 && j < 10 && wrongList.size() >= 4) array[i][j] = '-';
				if (((i == 4 && j == 5) || (i == 5 && j == 4)) && wrongList.size() >= 5) array[i][j] = '/';
				if (((i == 5 && j == 8) || (i == 4 && j == 7)) && wrongList.size() >= 6) array[i][j] = '\\';
				if (i == 6 && j >= 2 && j < 4 && wrongList.size() >= 7) array[i][j] ='-';
				if (i == 6 && j >= 9 && j < 11 && wrongList.size() >= 8) array[i][j] = '-';
				if (i == 7 && j != 0) array[i][j] = '_';
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 11; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
	}

	//Print out welcome message and rules for playing the game;
	//If we haven't won the game and we didn't hit the wrong guess limit, we handle each guess and print hangman;
	//Otherwise, game is over.
	public void playGame() {
		chooseWord();
		System.out.println("Welcome to Hangman!");
		System.out.println();
		System.out.println("You have maximum " + MAX_GUESS + " times to make mistakes.");
		System.out.println("The first line stands for the length of selected word.");

		System.out.println("Please enter a character (upper or lower), then hit 'ENTER': ");
		printHangman();
		
		while (!gameWon() && wrongList.size() < MAX_GUESS) {
			handleGuess();
			printHangman();
		} 

		gameOver();
	}

	//Use a char array to display the word, it is updated each time when we handle guess and print hangman
	//	it will print each character in the correctList at it's corresponding location,
	//	note here we use toLowerCase() and only print lower cases  
	public void displayWord() {
		char[] status = new char[selectedWord.length()];
		Arrays.fill(status, '_');

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