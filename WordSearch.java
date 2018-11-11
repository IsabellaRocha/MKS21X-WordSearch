import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class WordSearch{
    private char[][]data;
    private int row;
    private int col;
    private int seed;
    private Random randgen;
    private ArrayList<String>wordsToAdd = new ArrayList<String>();
    private ArrayList<String>wordsAdded = new ArrayList<String>();

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows, int cols, String fileName) throws FileNotFoundException {
      row = rows;
      col = cols;
      data = new char[rows][cols];
      randgen = new Random();
      seed = randgen.nextInt();
      clear();
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      while (in.hasNext()) {
        wordsToAdd.add(in.nextLine().toUpperCase());
      }
      addAllWords();
    }
    public WordSearch(int rows, int cols, String fileName, int randSeed) throws FileNotFoundException {
      row = rows;
      col = cols;
      data = new char[rows][cols];
      randgen = new Random(randSeed);
      clear();
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      while (in.hasNext()) {
        wordsToAdd.add(in.nextLine().toUpperCase());
      }
      addAllWords();
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      int idx = 0;
      while (idx < data.length) {
        int x = 0;
        while (x < data[idx].length) {
          data[idx][x] = '_';
          x += 1;
        }
        idx += 1;
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
      String output = "|";
      int idx = 0;
      while (idx < data.length) {
        int x = 0;
        while (x < data[idx].length) {
          output += data[idx][x] + " ";
          x += 1;
        }
        output += "|\n|";
        idx += 1;
      }
      output = output.substring(0,output.length() - 1) + "Words: " + wordsAdded;
      return output;
    }
    /**Attempts to add a given word to the specified position of the WordGrid.
    *The word is added in the direction rowIncrement,colIncrement
    *Words must have a corresponding letter to match any letters that it overlaps.
    *
    *@param word is any text to be added to the word grid.
    *@param row is the vertical locaiton of where you want the word to start.
    *@param col is the horizontal location of where you want the word to start.
    *@param rowIncrement is -1,0, or 1 and represents the displacement of each letter in the row direction
    *@param colIncrement is -1,0, or 1 and represents the displacement of each letter in the col direction
    *@return true when: the word is added successfully.
    *        false when: the word doesn't fit, OR  rowchange and colchange are both 0,
    *        OR there are overlapping letters that do not match
    */
    private boolean addWord(int r, int c, String word, int rowIncrement, int colIncrement) {
      word = word.toUpperCase();
      if (rowIncrement == 0 && colIncrement == 0) {
        return false;
      }
      if (r >= row || c >= col || r < 0 || c < 0) {
        return false;
      }
      if (rowIncrement > 1 || colIncrement > 1 || rowIncrement < 0 || colIncrement < 0) {
        return false;
      }
      if (r + word.length() * rowIncrement < 0 || r + word.length() * rowIncrement > row) {
        return false;
      }
      if (c + word.length() * colIncrement < 0 || c + word.length() * colIncrement > col) {
        return false;
      }
      int x = r;
      int y = c;
      int idx = 0;
      while (x < word.length() + r && y < word.length() + c) {
        if (data[x][y] != '_') {
          if (word.charAt(idx) != data[x][y]) {
            return false;
          }
        }
        x += rowIncrement;
        y += colIncrement;
        idx += 1;
      }
      x = r;
      y = c;
      idx = 0;
      while (x < word.length() + r && y < word.length() + c) {
        data[x][y] = word.charAt(idx);
        x += rowIncrement;
        y += colIncrement;
        idx += 1;
      }
      return true;
    }
    /*[rowIncrement,colIncrement] examples:
     *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
     *[ 1,0] would add downwards because (row+1), with no col change
     *[ 0,-1] would add towards the left because (col - 1), with no row change
     */

    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
    private void addAllWords() {
      int fails = 0;
      int idx = 0;
      String word;
      int r;
      int c;
      boolean check;
      while (wordsToAdd.size() > 0) {
        idx = Math.abs(randgen.nextInt() % wordsToAdd.size());
        word = wordsToAdd.get(idx).toUpperCase();
        int rowIncrement = randgen.nextInt() % 2;
        int colIncrement = randgen.nextInt() % 2;
        check = false;
        while (fails < 150 && !check) {
          r = Math.abs(randgen.nextInt() % row);
          c = Math.abs(randgen.nextInt() % col);
          if (addWord(r, c, word, rowIncrement, colIncrement)) {
            wordsAdded.add(wordsToAdd.remove(idx));
            check = true;
          }
          else {
            fails += 1;
          }
        }
        if (!check) {
          wordsToAdd.remove(idx);
        }
      }
    }
}
