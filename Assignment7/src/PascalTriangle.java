
public class PascalTriangle {
	public static void main(String[] args) {
		printPascalTriangle(7);
	}
	//Print first n lines of Pascal Triangle
	//O(n^3)
	public static void printPascalTriangle(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print(pascalUtil(i, j) + " ");
			}
			System.out.println();
		}
	}
	
	private static int pascalUtil(int row, int col) {
		if (row == 0 || col == 0 || col == row) {
			return 1;
		} else {
			return pascalUtil(row - 1, col) + pascalUtil(row - 1, col - 1);
		}
	}
}
