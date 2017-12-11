import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;

public class Assignment6_p1p2 {
	//2.
	public static void parse(File file) { // score 1
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
class MyIndexOutOfBoundException extends Exception { // score 2
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
