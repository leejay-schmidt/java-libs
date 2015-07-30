public class findCeleb {  
  public static int celebNum(boolean[][] party, int n) {
    boolean[] rowAllFalse = new boolean[n];
    boolean[] colAllTrue = new boolean[n];

    for (int i = 0; i < n; ++i) {
      rowAllFalse[i] = colAllTrue[i] = true;
    }
    int col, row;
    for (col = 0; col < n; ++col) {
      for (row = 0; row < n; ++row) {
        colAllTrue[col] = colAllTrue[col] & party[row][col];
        if (!colAllTrue[col]) break;
      }
    }
    for (row = 0; row < n; ++row) {
      for (col = 0; col < n; ++col) {
        if (row != col) {
          rowAllFalse[row] = rowAllFalse[row] & !party[row][col];
        }
        if (!rowAllFalse[row]) break;
      }
    }
    for (int j = 0; j < n; ++j) {
      if (rowAllFalse[j] && colAllTrue[j]) return j;
    }
    return -1;
  }

  public static void main(String[] args) {
    boolean[][] party = new boolean[][] {
      { true, true, false, false, true, true, false },
      { false, true, true, false, true, false, true },
      { true, false, true, true, true, false, false },
      { false, true, false, true, true, true, true },
      { false, false, false, true, true, false, false },
      { true, true, false, true, true, false, false },
      { true, false, false, false, true, false, false }
    };

    System.out.println(celebNum(party, 7));
  }
}
