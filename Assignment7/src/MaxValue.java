import java.util.Random;

public class MaxValue extends Thread { // score 1.5
	private int lo, hi;
	private int[] arr;
	private int max = 0; // Integer.MAX_VALUE
	
	public MaxValue(int[] arr, int lo, int hi) {
		this.arr = arr;
		this.lo = lo;
		this.hi = hi;
	}
	
	@Override
	public void run() {
		for (int i = lo; i < hi; i++) {
			max = Math.max(max, arr[i]);
		}
	}

	public static int findMax(int[] arr) throws InterruptedException {
		int len = arr.length;
		int max = 0;
		
		// Create and start 4 threads.
		MaxValue[] thread = new MaxValue[4];
		for (int i = 0; i < 4; i++) {
			thread[i] = new MaxValue(arr, (i * len) / 4, ((i + 1) * len / 4));
			thread[i].start();
		}
		
		// Wait for the threads to finish and sum their results.
		for (int i = 0; i < 4; i++) {
			thread[i].join();
			max = Math.max(max, thread[i].max);
        }
        return max;
	}
	
	public static void main(String[] args) throws InterruptedException {
		int[] arr = new int[100];
		Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt();
        }
//        for (int i = 0; i < arr.length; i++) {
//        	System.out.print(arr[i] + " ");
//        }
//        System.out.println();
        int max = findMax(arr);
        System.out.println("Max value is: " + max);
	}
}
