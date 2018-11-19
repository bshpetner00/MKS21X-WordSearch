import java.util.*;
import java.io.*;
public class WordSearch {
	private char[][] data;
	private int seed;
	private Random randgen;
	public ArrayList<String>wordsToAdd;
	public ArrayList<String>wordsAdded;
	public WordSearch(int rows, int cols, String filename) {
		seed = (int)(Math.random()*10000);
		if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException("Your dimensions don't make no sense");
		}
		data = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] = '_';
			}
		}
		randgen = new Random(seed);
		wordsToAdd = new ArrayList<>();
		wordsAdded = new ArrayList<>();
		try {
			File juul = new File(filename);
			Scanner myle = new Scanner(juul);
			while (myle.hasNext()) {
				wordsToAdd.add(myle.next().toUpperCase());
			}
		}
		catch(FileNotFoundException e) {
			throw new IllegalArgumentException("File not found.");
		}
		addAllWords();
		fill();
	}
	public WordSearch(int rows, int cols, String filename, int seedling) {
		seed = seedling;
		if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException("Your dimensions don't make no sense");
		}
		data = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] = '_';
			}
		}
		randgen = new Random(seed);
		wordsToAdd = new ArrayList<>();
		wordsAdded = new ArrayList<>();
		try {
			File juul = new File(filename);
			Scanner myle = new Scanner(juul);
			while (myle.hasNext()) {
				wordsToAdd.add(myle.next().toUpperCase());
			}
		}
		catch(FileNotFoundException e) {
			throw new IllegalArgumentException("File not found.");
		}
		addAllWords();
		fill();
	}
	public WordSearch(int rows, int cols, String filename, int seedling, boolean key) {
		seed = seedling;
		if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException("Your dimensions don't make no sense");
		}
		data = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] = '_';
			}
		}
		randgen = new Random(seed);
		wordsToAdd = new ArrayList<>();
		wordsAdded = new ArrayList<>();
		try {
			File juul = new File(filename);
			Scanner myle = new Scanner(juul);
			while (myle.hasNext()) {
				wordsToAdd.add(myle.next().toUpperCase());
			}
		}
		catch(FileNotFoundException e) {
			throw new IllegalArgumentException("File not found.");
		}
		addAllWords();
		if (!key) {
			fill();
		}
	}
	public void clear() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = '_';
			}
		}
	}
	public String toString() {
		String s = "| ";
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (j == 0 && i > 0) {
					s += "| " + data[i][j]+" ";
				}
				else {
					s += data[i][j] + " ";
				}
				if (j == data[i].length - 1) {
					s += "|\n";
				}
			}
		}
		s += "\nWords: " + wordsAdded + " Seed: " + seed;
		return s;
	}
	public boolean addWord(String word, int row, int col, int rInc, int cInc) {
		if (rInc == 0 && cInc == 0) {
			return false;
		}
		int length = word.length();
		if (length + row > data.length && rInc == 1) {
			return false;
		}
		else if (row - length < -1 && rInc == -1) {
			return false;
		}
		if (length + col > data[0].length && cInc == 1) {
			return false; 
		}
		else if (col - length < -1 && cInc == -1) {
			return false;
		}
		for (int i = 0; i < length; i++) {
			if (word.charAt(i) != data[row + rInc*i][col + cInc*i] && data[row + rInc*i][col + cInc*i] != '_') {
				return false;
			}
		}
		for (int i = 0; i < length; i++) {
			data[row + rInc*i][col + cInc*i] = word.charAt(i);
		}
		return true;
	}
	public ArrayList<String> getWordsToAdd() {
      return this.wordsToAdd;
    }
	public void addAllWords() {
	 	for (String word : this.getWordsToAdd()) {
	 		boolean done = false;
	 		for (int j = 0; j < 1000 && !done; j++) {
	 			int r = Math.abs(randgen.nextInt() % data.length);
	 			int c = Math.abs(randgen.nextInt() % data[0].length);
	 			int rI = randgen.nextInt() % 2;
	 			int cI = randgen.nextInt() % 2;
	 			if (addWord(word, r, c, rI, cI)) {
	 				done = true;
	 				wordsAdded.add(word);
	 			}
	 		}
	 	}
	}
	public void fill() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j] == '_') {
					data[i][j] = (char)(Math.abs(randgen.nextInt() % 26) + 65);
				}
			}
		}
	}
	public static void main(String[]args){
    	System.out.println(Arrays.toString(args));
  	}
}
