/* GOod Work
 * Score 10 + extra credit 2; Total score 10
 */
public class ExtraCredit {
	//DP
	public boolean findPartition(int[] arr) {
		if (arr == null || arr.length < 2) return false;
		
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		
		if (sum % 2 != 0) return false;
		int val = sum/2;
		//exist [i][j] is is true when there exist a subset of array equals i
		boolean[][] exist = new boolean[val + 1][arr.length + 1];
		//initialization
		for (int i = 0; i < arr.length; i++)
			exist[0][i] = true;
			
		//bottom up
		for (int i = 1; i <= val; i++) {
            for (int j = 1; j <= arr.length; j++) {
            	exist[i][j] = exist[i][j-1];
                if (i >= arr[j-1])
                	exist[i][j] = exist[i][j] || exist[i - arr[j-1]][j-1];
            }
        }
		return exist[val][arr.length];
	}
}
