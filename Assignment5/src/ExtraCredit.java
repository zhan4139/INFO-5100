import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ExtraCredit {
    public static void main(String[] args) {
        int[][] arr = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> res = spiralOrder(arr);
        System.out.println(Arrays.toString(res.toArray()));
    }
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();

        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        int dir = 0; //0 goes right, 1 goes down, 2 goes left and 3 goes up

        while (top <= bottom && left <= right) {
            if (dir == 0) {
                for (int i = left; i <= right; i++) res.add(matrix[top][i]);
                top++;
            } else if (dir == 1) {
                for (int i = top; i <= bottom; i++) res.add(matrix[i][right]);
                right--;
            } else if (dir == 2) {
                for (int i = right; i >= left; i--) res.add(matrix[bottom][i]);
                bottom--;
            } else if (dir == 3) {
                for (int i = bottom; i >= top; i--) res.add(matrix[i][left]);
                left++;
            }
            dir++;
            dir %= 4;
        }
        return res;
    }
}
