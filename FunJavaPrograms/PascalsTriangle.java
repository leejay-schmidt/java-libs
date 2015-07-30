public class PascalsTriangle {

  public static int[][] pascalsTriangle(int n) {
    int[][] triangle = new int[n][];
    for (int i = 0; i < n; ++i) {
      triangle[i] = new int[i+1];
      for (int j = 0; j < (i+1); ++j) {
        if (j == 0 || j == i) {
          triangle[i][j] = 1;
        }
        else {
          triangle[i][j] = triangle[i-1][j] + triangle[i-1][j-1];
        }
      }
    }
    return triangle;
  }

  public static void printTriangle(int[][] a, int n) {
   for(int i = 0; i < n; i++)
   {
      int paddingSize = (n-(i+1))/2;
      boolean odd = ((n-(i+1))%2 != 0);
      for (int j = 0; j < paddingSize; ++j) System.out.print("      ");
      if (odd) System.out.print("   ");
      printRow(a, i);
      System.out.println();
   }
  }
  public static void printRow(int[][] a, int row) {
    if (row == 0) {
      System.out.printf(" %4d ", a[row][row]);
    }
    else {
      for(int i = 0; i < row + 1; ++i) {
        System.out.printf(" %4d ", a[row][i]);
      }
    }
  }

  public static void main(String[] args) {
    printTriangle(pascalsTriangle(5), 5);
    System.out.println("\n");
    printTriangle(pascalsTriangle(1), 1);
    System.out.println("\n");
    printTriangle(pascalsTriangle(15), 15);
  }
}
