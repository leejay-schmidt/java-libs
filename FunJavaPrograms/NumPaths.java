import java.util.*;
import java.io.*;

public class NumPaths {
    private int n, m, total;
    private boolean[][] obstacles;
    
    public int getTotal() {
        return this.total;
    }
    public void changeVals(int n, int m, boolean[][] obstacles) {
        this.n = n;
        this.m = m;
        this.obstacles = obstacles;
        this.total = 0;
        System.out.println(this.findPaths());
    }
    private static boolean canGoDown(int n, int m, int r, int c, boolean[][] obstacles) {
        return ((r+1) < n && c < m && !obstacles[r+1][c]);
    }
    private static boolean canGoRight(int n, int m, int r, int c, boolean[][] obstacles) {
        return (r < n && (c+1) < m && !obstacles[r][c+1]);
    }
    private static Map<String, Integer> newIdxMap(int row, int col) {
        Map<String, Integer> s = new HashMap<String, Integer>();
        s.put("row", new Integer(row));
        s.put("col", new Integer(col));
        return s;
    }
    public int findPaths() {
        Stack<Map<String, Integer>> s = new Stack<Map<String, Integer>>();
        //set will act as dictionary for index in array
        Map<String, Integer> firstVal = newIdxMap(0, 0);
        s.push(firstVal);
        //use depth first search to get ALL possible paths
        while(!s.empty()) {
            Map<String, Integer> cur = s.pop();
            int r = cur.get("row").intValue(), c = cur.get("col").intValue();
            if(r == (n-1) && c == (m-1)) ++total;
            else {
                if(canGoDown(n, m, r, c, obstacles)) s.push(newIdxMap(r+1, c));
                if(canGoRight(n, m, r, c, obstacles)) s.push(newIdxMap(r, c+1));
            }
        }
        return total;
    }
    public NumPaths(int n, int m, boolean[][] obstacles) {
        this.n = n;
        this.m = m;
        this.obstacles = obstacles;
        this.total = 0;
        System.out.println(this.findPaths());
    }
    public static void main(String[] args) {
        boolean[][] boolArray = new boolean[3][3];
        NumPaths n = new NumPaths(3, 3, boolArray);
        boolArray[1][1] = true;
        n.changeVals(3, 3, boolArray);
        boolArray = new boolean[5][6];
        n.changeVals(5, 6, boolArray);
        boolArray[3][2] = true;
        boolArray[2][4] = true;
        n.changeVals(5, 6, boolArray);
        boolArray[4][2] = true;
        boolArray[3][3] = true;
        n.changeVals(5, 6, boolArray);
        boolArray[2][2] = true;
        boolArray[1][2] = true;
        boolArray[0][2] = true;
        n.changeVals(5, 6, boolArray);
    }
}
