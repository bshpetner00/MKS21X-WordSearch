public class driver {
  public static void main(String[]args) {
    WordSearch x = new WordSearch(10,10,"smd");
    x.addWord("fish", 9, 9, -1, -1);
    x.addWord("fish", 9, 9, -1, 0);
    x.addWord("fish", 9, 9, 0, -1);
    System.out.println(x.toString());
  }
}
