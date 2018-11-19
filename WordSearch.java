import java.util.*;
import java.io.*;
public class WordSearch {
	private char[][] data;
	private int seed;
	private Random randgen;
	private ArrayList<String>wordsToAdd;
	private ArrayList<String>wordsAdded;
	public WordSearch(int rows, int cols, String filename) {
		Random seed = new Random();
		if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException();
		}
		data = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] = '_';
			}
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
		String wordList = "";

		s += "\n Words: ";
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
	public static void main(String[]args){
    	System.out.println(Arrays.toString(args));
  	}
}
