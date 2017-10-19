import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;

public class Assignment6_p1p2 {
	public static void main(String[] args) {
		//Test Problem 1
		int[] arr = new int[] {1,2,3};
		try {
			for (int i = 0; i < 4; i++) {
				if (i >= 3) throw new MyIndexOutOfBoundException(i, 0, arr.length - 1);
				System.out.println(arr[i]);
			}
		} catch (MyIndexOutOfBoundException e) {
			System.out.println(e);
		}
		//Test Problem 2
		File a = new File("/home/ryan/Desktop/test");
		parse(a);
	}

	//2.
	public static void parse(File file) {
		RandomAccessFile input = null;
		String line = null;

		try {
			input = new RandomAccessFile(file, "r");
			while ((line = input.readLine()) != null) {
				System.out.println(line);
		  	}
		  	return;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    if (input != null) {
		    	try {
		    		input.close();
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
		    }
		}
  	}  

}

//1.
class MyIndexOutOfBoundException extends Exception {
	private int index;
	private int lowerBound;
	private int upperBound;

	public MyIndexOutOfBoundException(int index, int lowerBound, int upperBound) {
		this.index = index;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	@Override
	public String toString() {
		return "Error Message: Index: " + index + ", but Lower bound: " + lowerBound 
			+ ", Upper bound: " + upperBound;  
	}
}
