import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

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
		return s;
	}
	public boolean addWordHorizontal (String word, int row, int col) {
		if ((word.length() + col) > data[row].length ) {
			return false;
		}
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != data[row][col+i] && data[row][col+i] != '_') {
				return false;
			}
		}
		for (int i = 0; i < word.length();i++) {
			data[row][col+i] = word.charAt(i);
		}
		return true;
	}
	public boolean addWordVertical(String word, int row, int col) {
		if ((word.length()+row) > data[col].length) {
			return false;
		}
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != data[row+i][col] && data[row+i][col] != '_') {
				return false;
			}
		}
		for (int i = 0; i < word.length(); i++) {
			data[row+i][col] = word.charAt(i);
		}
		return true;
	}
	public boolean addWordDiagonal(String word, int row, int col) {
		if (((word.length() + col) > data[row].length) || (word.length()+row) > data[col].length) {
			return false;
		}
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != data[row+i][col+i] && data[row+i][col+i] != '_') {
				return false;
			}
		}
		for (int i = 0; i < word.length(); i++) {
			data[row+i][col+i] = word.charAt(i);
		}
		return true;
	}

}
