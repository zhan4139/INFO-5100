package midTerm;

import java.util.ArrayList;

public class Solution {

    // Q1
    public int[] reverseEvenIndices(int[] nums){
        int start = 0;
        int end = ((nums.length - 1) % 2 == 0) ? nums.length - 1 : nums.length - 2;
        for (int i = start ; i < end / 2 ; i += 2) {
            for (int j = end ; j > end / 2 ; j -= 2) {
                swap(nums, i , j);
            }
        }
        return nums;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Q2
    public int arrangeCoins(int n){
        int row = 1;
        int rem = n - 1;
        while (rem >= row + 1) {
            row++;
            rem -= row;
        }
        return n == 0 ? 0 : row;
    }

    // Q3
    public int minMoves(int[] nums) {
        int sum =0;
        int min = Integer.MAX_VALUE;
        for(int i : nums){
            sum += i;
            if(i < min) min = i;
        }
        return sum - (nums.length * min);
    }

    // Q4
    public int numberOfWays(int m, int n, int k){
        if(x < 1) {
            return 0;
        }
        if(n == 1 && x <= m) {
            return 1;
        }
        int sum = 0;
        for(int i = 1; i <= m; i++) {
            sum += countNumberOfPossibleWays(m,n-1,x-i);
        }
        return sum;
    }

    // Q5
    public ArrayList<Cell> findPath(int[][] maze) {
        ArrayList<Cell> result = new ArrayList<>();
        return findPath(maze,new Cell(0,0),result) ? result : new ArrayList<>();
    }
    private boolean findPath(int[][] maze, Cell cursor, ArrayList<Cell> result) {
        if(cursor.x == maze.length - 1 && cursor.y == maze[0].length - 1) {
            result.add(cursor);
            return true;
        }
        if(!isSafe(maze,cursor)) {
            return false;
        }
        result.add(cursor);
        Cell cursorMoveRight = new Cell(cursor.x+1, cursor.y);
        Cell cursorMoveDown = new Cell(cursor.x, cursor.y+1);
        if(findPath(maze,cursorMoveRight,result) || findPath(maze,cursorMoveDown,result)) {
            return true;
        }
        result.remove(cursor);
        return false;
    }
    private boolean isSafe(int[][] maze, Cell cursor){
        return cursor.x < maze.length &&
                cursor.y < maze[0].length &&
                maze[cursor.x][cursor.y] == 1;
    }
}

class Cell {
    int x,y;
    Cell(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "["+this.x +", "+this.y+"]";
    }
}