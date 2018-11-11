import java.util.*;
import java.io.*;

public class Driver{
  public static void main(String[] args) {
  if (args.length < 3) {
    System.out.println("Enter arguments for your puzzle like this: rows cols fileName (seed is optional)");
  }
  int rows = Integer.parseInt(args[0]);
  int cols = Integer.parseInt(args[1]);
  String fileName = args[2];
  int seed = 0;
  if (args.length > 3) {
    seed = Integer.parseInt(args[3]);
  }
  try {
    if (seed == 0) {
      WordSearch a = new WordSearch(rows, cols, fileName);
      System.out.println(a);
  }
    else {
      WordSearch a = new WordSearch(rows, cols, fileName, seed);
      System.out.println(a);
    }
  }
  catch(FileNotFoundException e) {
    System.out.println("File" + fileName + "does not exist");
    System.exit(1);
  }
}
}
