public class Driver{
  public static void main(String[] args){
    WordSearch first = new WordSearch(8, 5);

    System.out.println("---Testing Constructor---");
    System.out.println("testing toString: should print a 8-by-5 empty word search");
    System.out.println(first.toString());

    System.out.println("---Testing addWordHorizontal---");
    System.out.println("testing add YES horizontally to row 2, column 0: should return true");
    System.out.println(first.addWordHorizontal("YES", 2, 0));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());
    System.out.println("\ntesting add NO horizontally to row 1, column 3: should return true");
    System.out.println(first.addWordHorizontal("NO", 1, 3));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());

    System.out.println("\ntesting add YES horizontally to row 0, column 3: should return false");
    System.out.println(first.addWordHorizontal("YES", 0, 3));
    System.out.println("word search should be the same as before:");
    System.out.println(first.toString());

    System.out.println("\ntesting add YES horizontally to row 2, column 0 again: should return true");
    System.out.println(first.addWordHorizontal("YES", 2, 0));
    System.out.println("word search should be the same as before:");
    System.out.println(first.toString());
    System.out.println("\ntesting add YEA horizontally to row 2, column 0: should return false");
    System.out.println(first.addWordHorizontal("YEA", 2, 0));
    System.out.println("word search should be the same as before:");
    System.out.println(first.toString());

    first.addWordHorizontal("S", 3, 4);
    System.out.println("word search modified: added S to middle right corner");
    System.out.println(first.toString());
    System.out.println("\ntesting add NO horizontally to row 3, column 3: should return false");
    System.out.println(first.addWordHorizontal("NO", 3, 3));
    System.out.println("word search should be the same as before:");
    System.out.println(first.toString());

    System.out.println("\ntesting add ESA horzontally to row 2, column 1: should return true");
    System.out.println(first.addWordHorizontal("ESA", 2, 1));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());

    System.out.println("\ntesting add YOS horizontally to row 2, column 0: should return false");
    System.out.println(first.addWordHorizontal("YOS", 2, 0));
    System.out.println("word search be the same as before:");
    System.out.println(first.toString());

    System.out.println("---Testing addWordVertical---");
    System.out.println("testing add NO vertically to row 0, column 0: should return true");
    System.out.println(first.addWordVertical("NO", 0, 0));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());

    System.out.println("\ntesting add NOYS vertically to row 0, column 0: should return true");
    System.out.println(first.addWordVertical("NOYS", 0, 0));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());

    System.out.println("\ntesting add SUPER vertically to row 0, column 4: should return false");
    System.out.println(first.addWordVertical("SUPER", 0, 4));
    System.out.println("word search should be the same as before:");
    System.out.println(first.toString());

    System.out.println("\ntesting add SUPER vertically to row 2, column 2: should return true");
    System.out.println(first.addWordVertical("SUPER", 2, 2));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());

    System.out.println("\ntesting add EYES vertically to row 0, column 1: should return true");
    System.out.println(first.addWordVertical("EYES", 0, 1));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());

    System.out.println("\ntesting add POPE vertically to row 0, column 4: should return false");
    System.out.println(first.addWordVertical("POPE", 0, 4));
    System.out.println("word search should be the same as before");
    System.out.println(first.toString());

  }
}
