import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileAnalyzer {
	public static void main(String[] args) throws IOException {
		System.out.println("Filename: ");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		//String name = "data/Question2_test1.txt";
		FileCounter counter = new FileCounter();
		FileReader reader = new FileReader(name);
		Scanner fileIn = new Scanner(reader);
		counter.read(fileIn);
		fileIn.close();
		System.out.println("Characters: " + counter.getCharacterCount());
		System.out.println("Words: " + counter.getWordCount());
		System.out.println("Lines : " + counter.getLineCount());
	}
}

class FileCounter {

	private int characterCount, wordCount, lineCount;

	public FileCounter() {
		characterCount = 0;
		wordCount = 0;
		lineCount = 0;
	}

	/**
	 * Processes an input source and adds its character, word, and line counts
	 * to the respective variables.
	 * 
	 * @param in
	 *            the scanner to process
	 */
	public void read(Scanner in) throws IOException {

		while (in.hasNext()) {
			String s = in.nextLine();
			if (!s.equalsIgnoreCase("")) {
				String word = s.replaceAll("\\s+", ""); //replace white-spaces
				characterCount += word.length();
				wordCount += s.split(" ").length;
			}
			lineCount++;
		}
	}

	public int getCharacterCount() {
		return characterCount;
	}

	public int getWordCount() {
		return wordCount;
	}

	public int getLineCount() {
		return lineCount;
	}
}