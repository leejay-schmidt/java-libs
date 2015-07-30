import java.util.Scanner;

public class Sudoku {
  int[][] puzzle;

  public Sudoku(){
    puzzle = new int[9][9];
    printPuzzle();
  }

  public boolean submatrixIsValid(int row, int col) {
    boolean[] hashTable = new boolean[10];
    row = row/3;
    col = col/3;
    for(int i=row; i<row+3; ++i) {
      for(int j=col; j<col+3; ++j) {
        if(hashTable[puzzle[i][j]])
          if(puzzle[i][j] != 0)
            return false;
        else
          hashTable[puzzle[i][j]] = true;
      }
    }
    return true;
  }

  public void setValue(int row, int col, int val) {
    if(val < 1 || val > 9) {
      System.out.println("That value is illegal!");
      return;
    }
    if(row < 1 || col < 1 || row > 9 || col > 9) {
      System.out.println("Illegal row or column value!");
      return;
    }
    if(!submatrixCanInsert(row - 1, col - 1, val)) {
      System.out.println("That value exists in the submatrix!");
      return;
    }
    if(!colCanInsert(col - 1, val)) {
      System.out.println("That value exists in the column!");
      return;
    }
    if(!rowCanInsert(row - 1, val)) {
      System.out.println("That value exists in the row!");
      return;
    }
    puzzle[row - 1][col - 1] = val;
    printPuzzle();
  }

  public void printPuzzle() {
   for(int i = 0; i < 9; i++)
   {
      for(int j = 0; j < 9; j++)
      {
        if (puzzle[i][j] == 0)
          System.out.printf("   ");
        else
          System.out.printf(" %d ", puzzle[i][j]);
        if (j < 8)
          System.out.printf("|");
      }
      System.out.println();
      if(i < 8)
        System.out.println("-----------------------------------");
   }
   System.out.println("\n\n");
  }

  public boolean submatrixCanInsert(int row, int col, int val) {
    boolean[] hashTable = new boolean[10];
    row = row/3;
    col = col/3;
    hashTable[val] = true;
    for(int i=row; i<row+3; ++i) {
      for(int j=col; j<col+3; ++j) {
        //System.out.println("Row: " + i + " Col: " + j);
        if(hashTable[puzzle[i][j]])
           if(puzzle[i][j] != 0) {
             //System.out.println("leaving at value " + i + " " + j);
             return false;
           }
        else
          hashTable[puzzle[i][j] ] = true;
      }
    }
    return true;
  }

  public boolean colIsValid(int col) {
    boolean[] hashTable = new boolean[10];
    for(int i=0; i<9; ++i) {
      if(hashTable[puzzle[i][col]])
        if(puzzle[i][col] != 0)
          return false;
      else
        hashTable[puzzle[i][col]] = true;
    }
    return true;
  }

  public boolean colCanInsert(int col, int val) {
    boolean[] hashTable = new boolean[10];
    hashTable[val] = true;
    for(int i=0; i<9; ++i) {
      if(hashTable[puzzle[i][col]])
        if(puzzle[i][col] != 0)
          return false; 
      else
        hashTable[puzzle[i][col]] = true;
    }
    return true;
  }

  public boolean rowIsValid(int row) {
    boolean[] hashTable = new boolean[10];
    for(int i=0; i<9; ++i) {
      if(hashTable[puzzle[row][i]])
        if(puzzle[row][i] != 0) 
          return false;
      else
        hashTable[puzzle[row][i]] = true;
    }
    return true;
  }

  public boolean rowCanInsert(int row, int val) {
    boolean[] hashTable = new boolean[10];
    hashTable[val] = true;
    for(int i=0; i<9; ++i) {
      if(hashTable[puzzle[row][i]])
        if(puzzle[row][i] != 0)
          return false;
      else
        hashTable[puzzle[row][i]] = true;
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println("When prompted to enter a value, entering 0 will exit the");
    System.out.println("program and -1 will print the puzzle");
    Sudoku newPuzzle = new Sudoku();
    Scanner in = new Scanner(System.in);
    while(true) {
      System.out.println("Please enter your desired value (or 0 to exit, -1 to print)");
      int nextVal = in.nextInt();
      System.out.println();
      if(nextVal == 0) break;
      if(nextVal == -1) newPuzzle.printPuzzle();
      else {
        System.out.println("Please enter the row:");
        int row = in.nextInt();
        System.out.println("Please enter the column:");
        int col = in.nextInt();
        newPuzzle.setValue(row, col, nextVal);
      }
    }
  }
}


