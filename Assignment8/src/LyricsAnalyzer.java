import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LyricsAnalyzer { // score 4
	//For testing
//	public static void main(String[] args) throws IOException {
//		File name = new File("data/Question2_test4.txt");
//		LyricsAnalyzer counter = new LyricsAnalyzer();
//		counter.read(name);
//		counter.displayWords();
//		System.out.println("Map has #distinct words: " + counter.count());
//		System.out.println("Most frequent word is: " + counter.mostFrequentWord());
//		File f = new File("data/temp.txt");
//		counter.writeLyrics(f);
//	}

	private HashMap<String, ArrayList<Integer>> map;
	 int wordCount;

	public LyricsAnalyzer() {
		map = new HashMap<>();
		wordCount = 0;
	}

	public void read(File file) throws IOException {

		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);

		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			String[] tokens = line.split("\\s+");
			for (int i = 0; i < tokens.length; i++) {
				int index = 0;
				if (i == tokens.length - 1)
					index = -(i + wordCount + 1);
				else
					index = i + wordCount + 1;
				add(tokens[i], index);
			}
			wordCount += tokens.length;
		}
		
		br.close();
		reader.close();
	}

	private void add(String lyricWord, int wordPosition) {
		if (map.containsKey(lyricWord)) {
			map.get(lyricWord).add(wordPosition);
		} else {
			ArrayList<Integer> l = new ArrayList<>();
			l.add(wordPosition);
			map.put(lyricWord, l);
		}
	}

	public void displayWords() {
		TreeSet<String> set = new TreeSet<>(map.keySet());
		Iterator<String> itor = set.iterator();
		while (itor.hasNext()) {
			String s = itor.next();
			System.out.println(s + ": " + map.get(s)
				.stream()
				.map(Object::toString)
				.collect(Collectors.joining(", ")));
		}
	}

	public void writeLyrics(File file) throws IOException {
		String[] arr = new String[wordCount + 1];
		for (HashMap.Entry<String, ArrayList<Integer>> i : map.entrySet()) {
			ArrayList<Integer> l = i.getValue();
			String s = i.getKey().toUpperCase();
			for (Integer pos : l) {
				if (pos < 0)
					arr[-pos] = s + "\n";
				else arr[pos] = s + " ";
			}
		}
		
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (int i = 1; i < arr.length; i++) {
			bw.write(arr[i]);
		}
		
		bw.close();
		fw.close();
	}

	public int count() {
		return map.size();
	}

	public String mostFrequentWord() {
		String res = "";
		int max = 0;
		for (HashMap.Entry<String, ArrayList<Integer>> i : map.entrySet()) {
			if (i.getValue().size() > max) {
				max = i.getValue().size();
				res = i.getKey();
			}
		}
		return res;
	}
}
