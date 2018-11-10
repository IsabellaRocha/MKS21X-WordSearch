import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
public class WordSearch{
    private char[][]data;
    private int row;
    private int col;
    private int seed;
    private Random randgen;
    private ArrayList<String>wordsToAdd;
    private ArrayList<String>wordsAdded;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows, int cols, String fileName) {
      row = rows;
      col = cols;
      data = new char[rows][cols];
      randgen = new Random();
      seed = randgen.nextInt();
      clear();
      try {
        File f = new File(fileName);
        Scanner in = new Scanner(f);
        String output = "";
        while (in.hasNext()) {
          output += in.nextLine() + ", ";
          wordsToAdd.add(in.nextLine());
        }
      }
      catch (FileNotFoundException e) {
        System.out.println("File not found");
        System.exit(1);
      }
    }
    public WordSearch(int rows, int cols, String fileName, int randSeed) {
      row = rows;
      col = cols;
      data = new char[rows][cols];
      randgen = new Random();
      seed = randSeed;
      clear();
      try {
        File f = new File(fileName);
        Scanner in = new Scanner(f);
        String output = "";
        while (in.hasNext()) {
          output += in.nextLine() + ", ";
          wordsToAdd.add(in.nextLine());
        }
      }
      catch (FileNotFoundException e) {
        System.out.println("File not found");
        System.exit(1);
      }
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
          output += data[idx][x];
          x += 1;
        }
        output += "|\n|";
        idx += 1;
      }
      output = output.substring(0,output.length() - 1) + "Words: ";
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
      if (rowIncrement == 0 && colIncrement == 0) {
        return false;
      }
      if (r >= row || c >= col) {
        return false;
      }
      if (word.length() + c > col || word.length() + r > row) {
        return false;
      }
      if (c - word.length() < 0 || r - word.length() < row) {
        return false;
      }
      int x = r;
      int y = c;
      while (x < word.length() + r) {
        if (data[x][y] != '_') {
          if (word.charAt(x - row) != data[x][y]) {
            return false;
          }
        }
        x += rowIncrement;
        y += colIncrement;
      }
      x = r;
      y = c;
      while (x < word.length() + r) {
        data[x][y] = word.charAt(x - r);
        x += rowIncrement;
        y += colIncrement;
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
    public boolean addWordHorizontal(String word,int row, int col){
      if ((row >= this.row) || (word.length() + col > this.col)) {
        return false;
      }
      int idx = col;
      while (idx < word.length() + col) {
        if (data[row][idx] != '_') {
          if (word.charAt(idx - col) != data[row][idx]) {
            return false;
          }
        }
        idx += 1;
      }
      int x = col;
      while (x < word.length() + col) {
        data[row][x] = word.charAt(x - col);
        x += 1;
      }
      return true;
    }

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
    public boolean addWordVertical(String word,int row, int col){
      if ((col >= this.col) || (word.length() + row > this.row)) {
        return false;
      }
      int idx = row;
      while (idx < word.length() + row) {
        if (data[idx][col] != '_') {
          if (word.charAt(idx - row) != data[idx][col]) {
            return false;
          }
        }
        idx += 1;
      }
      int x = row;
      while (x < word.length() + row) {
        data[x][col] = word.charAt(x - row);
        x += 1;
      }
      return true;
    }
    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top left to bottom right, must fit on the WordGrid,
     *and must have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     */
    public boolean addWordDiagonal(String word,int row, int col){
      if ((word.length() + row > this.row) || (word.length() + col > this.col)) {
        return false;
      }
      int x = row;
      int y = col;
      while (x < word.length() + row) {
        if (data[x][y] != '_') {
          if (word.charAt(x - row) != data[x][y]) {
            return false;
          }
        }
        x += 1;
        y += 1;
      }
      x = row;
      y = col;
      while (x < word.length() + row) {
        data[x][y] = word.charAt(x - row);
        x += 1;
        y += 1;
      }
      return true;
    }
}
