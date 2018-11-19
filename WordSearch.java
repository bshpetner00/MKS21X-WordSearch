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
	public WordSearch(int rows, int cols, String filename, int seedling, String key) {
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
		if (key != "key") {
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
		System.out.println("Thank you for participating in the beta. You should know what to do considering that you devised this assignment but if you happen to make any mistakes my handy error messages should sort you out real nice.");
    	try {
    		if (args.length <= 2) {
    			System.out.println("You need at least three arguments.");
    			System.exit(1);
    		}
    		else if (args.length == 3) {
    			int rows = Integer.parseInt(args[0]);
    			int cols = Integer.parseInt(args[1]);
    			if (rows < 0 || cols < 0) {
    				System.out.println("Why would you try to make negative rows or columns?");
    				System.exit(1);
    			}
    			String file = args[2];
    			WordSearch x = new WordSearch(rows,cols,file);
    			System.out.println(x.toString());
    		}
    		else if (args.length == 4) {
    			int rows = Integer.parseInt(args[0]);
    			int cols = Integer.parseInt(args[1]);
    			if (rows < 0 || cols < 0) {
    				System.out.println("Why would you try to make negative rows or columns?");
    				System.exit(1);
    			}
    			String file = args[2];
    			int seed = Integer.parseInt(args[3]);
    			if (seed < 0 || seed > 10000) {
    				System.out.println("Seed must be between 0 and 10000 inclusive.");
    				System.exit(1);
    			}
    			WordSearch x = new WordSearch(rows,cols,file,seed);
    			System.out.println(x.toString());
    		}
    		else if (args.length == 5) {
    			int rows = Integer.parseInt(args[0]);
    			int cols = Integer.parseInt(args[1]);
    			if (rows < 0 || cols < 0) {
    				System.out.println("Why would you try to make negative rows or columns?");
    				System.exit(1);
    			}
    			String file = args[2];
    			int seed = Integer.parseInt(args[3]);
    			String keebler = args[4];
    			WordSearch x = new WordSearch(rows,cols,file,seed,keebler);
    			System.out.println(x.toString());
    		}
    	}
    	catch(NegativeArraySizeException e) {
    		System.out.println("You can't create a negative sized array. Try again with positive numbers");
    		System.exit(1);
    	}
    	catch(ArrayIndexOutOfBoundsException e) {
    		System.out.println("You can't size the array like that. Try again with positive numbers.");
    		System.exit(1);
    	}
    	catch(NumberFormatException e) {
    		System.out.println("Integers only!!!");
    		System.exit(1);
    	}
  	}
}
