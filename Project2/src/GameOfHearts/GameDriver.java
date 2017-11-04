package GameOfHearts;

import java.util.Scanner;

public class GameDriver {

	public static void main(String[] args) {
		String ans;
		do {
			Game game = new Game(4);
			game.playAGame();
			System.out.println("Player another game? Y/N");
			Scanner in = new Scanner(System.in);
			ans = in.next();
		} while (ans.equals("y") || ans.equals("Y"));
	}

}
