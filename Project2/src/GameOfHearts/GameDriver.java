/* Good Work
 * score 19
 * you are not setting spades as shortest if they are equal or less than diamonds and clubs and don't contains ACE or KING or QUEEN. check the requirements
 */
package GameOfHearts;

import java.util.Scanner;

public class GameDriver {

	public static void main(String[] args) {
		String ans;
		do {
			Game game = new Game(5);
			game.playAGame();
			System.out.println("Player another game (y/n)?");
			Scanner in = new Scanner(System.in);
			ans = in.next();
		} while (ans.equals("y") || ans.equals("Y"));
	}

}
