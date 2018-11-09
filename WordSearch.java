public class WordSearch {
	private char[][] data;
	public WordSearch(int rows, int cols) {
		data = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] = '_';
			}
		}
	}
	private void clear() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = '_';
			}
		}
	}
	public String toString() {
		String s = "";
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				s += data[i][j] + " ";
				if (j == data[i].length - 1) {
					s += "\n";
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

}