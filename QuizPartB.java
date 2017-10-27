import java.util.*;
public class QuizPartB {
	public static void main(String[] args) {
		
		//1. 
		int[] arr = {9,4,8,7,5,1,3};
		System.out.println(Arrays.toString(reverseEvenIndices(arr)));//3,4,5,7,8,1,9

		//2.
		System.out.println(arrangeCoins(8));//3

		//3.
		int[] arr2 = {1,2,3};
		System.out.println(minMoves(arr2));//3

		//4.
		System.out.println(countNumberOfPossibleWays(3, 2, 4));//3

		//5.
		List<Cell> res = new ArrayList<>();
		int[][] maze = {
			{1, 1, 0, 0},
			{1, 1, 1, 1},
			{1, 1, 0, 0},
			{1, 1, 1, 1}
		};

		res = findPath(maze);
		for (int i = 0; i < res.size(); i ++) {
			System.out.print(res.get(i).toString() + " ");
		}
		//[0, 0] [1, 0] [1, 1] [2, 1] [3, 1] [3, 2] [3, 3]
		System.out.println();
	}

	//1. Reverse Even Indices
	public static int[] reverseEvenIndices(int[] nums) {
		int[] res = nums.clone();
		if (nums == null || nums.length <= 2) return res;
		
		//two pointers, if length is odd, end = nums.length - 2
		int start = 0, end = nums.length - 1;
		if (nums.length % 2 == 0) {
			end = nums.length - 2;
		}

		//start swaping even indices
		while (start < end) {
			int temp = res[start];
			res[start] = res[end];
			res[end] = temp;
			start += 2;
			end -= 2;
		}

		return res;
	}

	//2. Arrange coins in stair shape
	public static int arrangeCoins(int n) {
		if (n <= 0) return 0;
		
		int level = 0;
		while (n > level) {
			level ++;
			n -= level;
		}

		return level;
	}

	//3. Minimum moves to make all elements equal
	public static int minMoves(int[] nums) {
		if (nums == null || nums.length == 0) return 0;

		//increment 1 for n - 1 numbers is the same as decrement 1 for 1 number
		//Until all number equals minimum number of the array, the total move is what we want
		int min = nums[0];
		int res = 0;//counting moves
		
		//find minimum
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < min) {
				min = nums[i];
			}	
		}

		//counting moves
		for (int i = 0; i < nums.length; i++) {
			res += (nums[i] - min);
		}

		return res;
	}
	

	//4. Count ways of throwing n dices with m faces that sum to x
	//E.g. 3 dices with 2 faces has 3 ways sum to 4 ([2, 1, 1], [1, 1, 2], [1, 2, 1])
	public static int countNumberOfPossibleWays(int m, int n, int x) {
		//Since the problems find n dices sums to x can be 
		//divided to subporblems find n - 1 problems sums to (x - (1~m))
		//Then each sub problems can be calculated by its subprob till 1 dice sum to 1 with 1 faces value 1
		//I'm going to use DP
		
		//n for dices and x for sum values, we usually use one extra space for each
		//the array is for counting ways n dice sum to x
		int[][] dp = new int[n + 1][x + 1]; 

		int j = 0;// value of x's
		
		while (j <= x && j <= m) {
			dp[1][j++] = 1; //intialize 1 dices, because 1 dice sum to j has only 1 way
		}

		for (int i = 2; i <= n; i ++) {//dices 2 to n
			for (j = 1; j <= x; j ++) {//sum values from 1 to x (j has already declared)
				for (int k = 1; k <= m && k <= j; k++) {//numbers at subproblem with dice value k
					dp[i][j] += dp[i - 1][j - k];
				}

			}
		}

		return dp[n][x];
	}

	//5. Extra Credit
	//Using backtracking
	public static ArrayList<Cell> findPath(int[][] maze) {
		ArrayList<Cell> res = new ArrayList<>();
		if (maze == null || maze.length <= 0) return res;
		findPath(res, new ArrayList<Cell>(), maze, 0, 0);
		return res;
	}

	private static void findPath(ArrayList<Cell> res, ArrayList<Cell> temp, int[][] maze, int i, int j) {
		//temp is for all paths and res is the result list, i stands for rows and j stands for cols
		if (i >= maze.length || j >= maze[0].length || maze[i][j] == 0) return;

		if (i == maze.length - 1 && j == maze[0].length - 1 && res.isEmpty()) {//only add one time and return one path
			temp.add(new Cell(i, j));
			for (Cell cell : temp) {//copy the path to result list
				res.add(cell);
			}
			return;
		}
		
		temp.add(new Cell(i, j));
		
		findPath(res, temp, maze, i + 1, j);//go right
		findPath(res, temp, maze, i, j + 1);//go down
		
	}
} 

class Cell {
	int x, y;
	Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
 	}
}