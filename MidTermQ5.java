import java.util.*;
public class MidTermQ5 {
	public static void main(String[] args) {

		List<Cell> res = new ArrayList<>();
		int[][] maze = {
			{1,1,0,1,0},
			{1,1,1,1,1},
			{1,0,0,1,0},
			{1,1,1,1,1}};


		res = findPath(maze);
		for (int i = 0; i < res.size(); i ++) {
			System.out.print(res.get(i).toString() + " ");
		}
		//[0, 0] [1, 0] [1, 1] [1, 2] [1, 3] [2, 3] [3, 3] [3, 4]
		System.out.println();
		
	}

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

		temp.remove(temp.size() - 1);
		
	}
}