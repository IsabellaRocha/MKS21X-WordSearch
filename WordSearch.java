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
     *and fill all of the positions with ' '
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows, int cols, String fileName) throws FileNotFoundException {
      if (rows <= 0 || cols <= 0) {
        throw new IllegalArgumentException();
      }
      row = rows;
      col = cols;
      data = new char[rows][cols];
      seed = (int)(Math.random() * 10000);
      randgen = new Random(seed);
      clear();
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      while (in.hasNext()) {
        wordsToAdd.add(in.next().toUpperCase());
      }
      addAllWords();
      fillInLetters();
    }
    public WordSearch(int rows, int cols, String fileName, int randSeed, boolean answer) throws FileNotFoundException {
      if (rows <= 0 || cols <= 0 || randSeed < 0 || randSeed > 10000) {
        throw new IllegalArgumentException();
      }
      row = rows;
      col = cols;
      data = new char[rows][cols];
      seed = randSeed;
      randgen = new Random(randSeed);
      clear();
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      while (in.hasNext()) {
        wordsToAdd.add(in.next().toUpperCase());
      }
      addAllWords();
      if (!answer) {
        fillInLetters();
      }
    }
    /**Set all values in the WordSearch to spaces' '*/
    private void clear(){
      int idx = 0;
      while (idx < data.length) {
        int x = 0;
        while (x < data[idx].length) {
          data[idx][x] = ' ';
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
      String output = "| ";
      int idx = 0;
      while (idx < data.length) {
        int x = 0;
        while (x < data[idx].length) {
          output += data[idx][x] + " ";
          x += 1;
        }
        output += "|\n| ";
        idx += 1;
      }
      String x = wordsAdded.toString();
      output = output.substring(0,output.length() - 1) + "Words: " + x.substring(1, x.length() - 1);
      output += "\nSeed: " + seed;
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
    /*[rowIncrement,colIncrement] examples:
     *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
     *[ 1,0] would add downwards because (row+1), with no col change
     *[ 0,-1] would add towards the left because (col - 1), with no row change
     */
    private boolean addWord(int r, int c, String word, int rowIncrement, int colIncrement) {
      word = word.toUpperCase();
      if (rowIncrement == 0 && colIncrement == 0) {
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
      if (rowIncrement == 1 || colIncrement == 1) {
        while (x < word.length() + r && y < word.length() + c) {
          if (data[x][y] != ' ') {
            if (word.charAt(idx) != data[x][y]) {
              return false;
            }
          }
          x += rowIncrement;
          y += colIncrement;
          idx += 1;
        }
      }
      if (rowIncrement == -1 || colIncrement == -1) {
        while (x > r - word.length() && y > c - word.length()) {
          if (data[x][y] != ' ') {
            if (word.charAt(idx) != data[x][y]) {
              return false;
            }
          }
          x += rowIncrement;
          y += colIncrement;
          idx += 1;
        }
      }
      x = r;
      y = c;
      idx = 0;
      if (rowIncrement == 1 || colIncrement == 1) {
        while (x < word.length() + r && y < word.length() + c) {
        data[x][y] = word.charAt(idx);
        x += rowIncrement;
        y += colIncrement;
        idx += 1;
      }
    }
    else if (rowIncrement == -1 || colIncrement == -1) {
      while (x > r - word.length() && y > c - word.length()) {
      data[x][y] = word.charAt(idx);
      x += rowIncrement;
      y += colIncrement;
      idx += 1;
      }
    }
    wordsToAdd.remove(word);
    wordsAdded.add(word);
    return true;
  }
    private void addAllWords() {
      while (wordsToAdd.size() > 0) {
        int idx = Math.abs(randgen.nextInt() % wordsToAdd.size());
        String word = wordsToAdd.get(idx).toUpperCase();
        boolean check = false;
        int fails = 0;
        while (fails < 150 && !check) {
          int r = Math.abs(randgen.nextInt() % row);
          int c = Math.abs(randgen.nextInt() % col);
          int rowIncrement = randgen.nextInt() % 2;
          int colIncrement = randgen.nextInt() % 2;
          if (addWord(r, c, word, rowIncrement, colIncrement)) {
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
    private void fillInLetters() {
      int idx = 0;
      while (idx < data.length) {
        int x = 0;
        while (x < data[idx].length) {
          if (data[idx][x] == ' ') {
            data[idx][x] = (char)((Math.abs(randgen.nextInt()) % 26) + 65);
          }
          x += 1;
        }
        idx += 1;
      }
    }
    public static void arguments(String[] args) {
      if (args.length < 3) {
        throw new NumberFormatException();
      }
    }
    public static void main(String[] args) {
      int rows;
      int cols;
      int seed = 0;
      try {
        arguments(args);
        rows = Integer.parseInt(args[0]);
        cols = Integer.parseInt(args[1]);
      }
      catch(NumberFormatException e) {
        System.out.println("Usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
        System.out.println("Rows cols and filename are necessary, randomSeed and answers are optional");
        System.out.println("Rows, columns, and the seed should be positive integers with the seed not exceeding 10,000");
        System.exit(1);
      }
      if (args.length > 3) {
        try {
          seed = Integer.parseInt(args[3]);
        }
        catch(NumberFormatException e) {
          System.out.println("Usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
          System.out.println("Rows cols and filename are necessary, randomSeed and answers are optional");
          System.out.println("Rows, columns, and the seed should be positive integers with the seed not exceeding 10,000");
          System.exit(1);
        }
      }
      String fileName = args[2];
      boolean answer = false;
      rows = Integer.parseInt(args[0]);
      cols = Integer.parseInt(args[1]);
      if (args.length > 3) {
        seed = Integer.parseInt(args[3]);
      }
      if (args.length > 4) {
        if (args[4].equals("key"))  {
          answer = true;
        }
      }
      try {
        if (seed == 0) {
          WordSearch a = new WordSearch(rows, cols, fileName);
          System.out.println(a);
        }
        else {
          WordSearch a = new WordSearch(rows, cols, fileName, seed, answer);
          System.out.println(a);
        }
      }
      catch(FileNotFoundException e) {
        System.out.println("File " + fileName + " does not exist");
        System.out.println("Usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
        System.out.println("Rows cols and filename are necessary, randomSeed and answers are optional");
        System.out.println("Rows, columns, and the seed should be positive integers with the seed not exceeding 10,000");
        System.exit(1);
      }
      catch (IllegalArgumentException e) {
        System.out.println("Usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
        System.out.println("Rows cols and filename are necessary, randomSeed and answers are optional");
        System.out.println("Rows, columns, and the seed should be positive integers with the seed not exceeding 10,000");
        System.exit(1);
      }
    }
}
