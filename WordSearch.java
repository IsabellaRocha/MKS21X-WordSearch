public class WordSearch{
    private char[][]data;
    private int row;
    private int col;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols){
      row = rows;
      col = cols;
      data = new char[rows][cols];
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
      String output = "";
      int idx = 0;
      while (idx < data.length) {
        int x = 0;
        while (x < data[idx].length) {
          output += data[idx][x] + " ";
          x += 1;
        }
        output += "\n";
        idx += 1;
      }
      return output;
    }


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
      if ((row >= this.row || col >= this.col) || (word.length() + col > this.col)) {
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
      if ((row >= this.row || col >= this.col) || (word.length() + row > this.row)) {
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
}
