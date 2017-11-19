import java.util.*;
public class MidTermQ4 {
	public static void main(String[] args) {
		System.out.println(numberOfWays(3, 2, 5));
	}

	public static int numberOfWays(int n, int m, int x) {
		if(x < 1) {
            return 0;
        }
        if(n == 1 && x <= m) {
            return 1;
        }
		List<List<Integer>> res = new ArrayList<>();
		numberOfWays(res, new ArrayList<>(), m, n, x);
		return res.size();

	}

	public static void numberOfWays(List<List<Integer>> res, List<Integer> temp, int m, int n, int x) {
		if (m < 1 || n < 1) return;
		if (temp.size() == n) {
			if (x == 0)
				res.add(temp);
			return;
		}

		for (int i = 1; i <= m; i++) {
			temp.add(i);
			numberOfWays(res, temp, m, n, x - i);
			temp.remove(temp.size() - 1);
		}
	}
}