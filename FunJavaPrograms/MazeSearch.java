import java.util.*;

@SuppressWarnings("unchecked")
public class MazeSearch {
  int[][] maze;
  boolean[][] visited;
  int width, height;
  private ArrayList<MazePoint> shortestPath;
  public MazeSearch(int[][] mazeIn) {
    maze = mazeIn;
    width = maze[0].length;
    height = maze.length;
    visited = new boolean[height][width];
  }
  public boolean pathExists(int sR, int sC, int eR, int eC) {
    visited[sR][sC] = true;
    return dfs(sR, sC, eR, eC);
  }
  public ArrayList<MazePoint> shortestPath() {
    if (shortestPath==null) {
      return new ArrayList();
    }
    else {
      return this.shortestPath;
    }
  }
  public boolean down(int r, int c) {
    return ((r+1)<height) && !visited[r+1][c] && (maze[r+1][c]==1);
  }
  public boolean up(int r, int c) {
    return ((r-1)>-1) && !visited[r-1][c] && (maze[r-1][c]==1);
  }
  public boolean right(int r, int c) {
    return ((c+1)<width) && !visited[r][c+1] && (maze[r][c+1]==1);
  }
  public boolean left(int r, int c) {
    return ((c-1)>-1) && !visited[r][c-1] && (maze[r][c-1]==1);
  }
  public boolean dfs(int r, int c, int eR, int eC) {
    if (r==eR && c==eC) return true;
    System.out.println("DFS on row: " + r + " col: " + c);
    if (up(r,c)) {
      visited[r-1][c] = true;
      if (dfs(r-1,c,eR,eC)) return true;
    }
    if (left(r,c)) {
      visited[r][c-1] = true;
      if (dfs(r,c-1,eR,eC)) return true;
    }
    if (right(r,c)) {
      visited[r][c+1] = true;
      if (dfs(r,c+1,eR,eC)) return true;
    }
    if (down(r,c)) {
      visited[r+1][c] = true;
      if (dfs(r+1,c,eR,eC)) return true;
    }
    return false;
  }
  public boolean isWall(int r, int c) {
    return (maze[r][c]==0);
  }
  public int bfs(int r, int c, int eR, int eC) {
    return bfs(r, c, eR, eC, false);
  }
  public int bfs(int r, int c, int eR, int eC, boolean printSteps) {
    Queue<MazePoint> q = new LinkedList<MazePoint>();
    MazePoint sP = new MazePoint(r, c, 0);
    sP.setPath(new ArrayList<MazePoint>());
    sP.addPointToPath(sP);
    //int[] sP = {r, c, 0};
    q.add(sP);
    visited = new boolean[height][width];
    visited[r][c] = true;
    while (q.peek() != null) {
      MazePoint point = q.remove();
      int[] pt = point.getPointArray();
      
      System.out.println("BFS on row: " + pt[0] + " col: " + pt[1]);
      if (up(pt[0],pt[1])) {
        if ((pt[0]-1==eR)&&(pt[1]==eC)) {
          MazePoint finalPoint = new MazePoint(pt[0]-1, pt[1], pt[2]+1);
          finalPoint.setPath(point.path());
          finalPoint.addPointToPath(finalPoint);
          this.shortestPath = finalPoint.path();
          return pt[2]+1;
        }
        else {
          visited[pt[0]-1][pt[1]] = true;
          MazePoint nextpt = new MazePoint(pt[0]-1, pt[1], pt[2] + 1);
          nextpt.setPath(point.path());
          nextpt.addPointToPath(nextpt);
          //int[] nextpt = {pt[0]-1, pt[1], pt[2]+1};
          q.add(nextpt);
        }
      }
      if (left(pt[0],pt[1])) {
        if ((pt[0]==eR)&&(pt[1]-1==eC)) {
          MazePoint finalPoint = new MazePoint(pt[0], pt[1]-1, pt[2]+1);
          finalPoint.setPath(point.path());
          finalPoint.addPointToPath(finalPoint);
          this.shortestPath = finalPoint.path();
          return pt[2]+1;
        }
        else {
          visited[pt[0]][pt[1]-1] = true;
          MazePoint nextpt = new MazePoint(pt[0], pt[1]-1, pt[2]+1);
          nextpt.setPath(point.path());
          nextpt.addPointToPath(nextpt);
          //int[] nextpt = {pt[0], pt[1]-1, pt[2]+1};
          q.add(nextpt);
        }
      }
      if (down(pt[0],pt[1])) {
        if ((pt[0]+1==eR)&&(pt[1]==eC)) {
          MazePoint finalPoint = new MazePoint(pt[0]+1, pt[1], pt[2]+1);
          finalPoint.setPath(point.path());
          finalPoint.addPointToPath(finalPoint);
          this.shortestPath = finalPoint.path();
          return pt[2]+1;
        }
        else {
          visited[pt[0]+1][pt[1]] = true;
          MazePoint nextpt = new MazePoint(pt[0]+1, pt[1], pt[2]+1);
          nextpt.setPath(point.path());
          nextpt.addPointToPath(nextpt);
          //int[] nextpt = {pt[0]+1, pt[1], pt[2]+1};
          q.add(nextpt);
        }
      }
      if (right(pt[0],pt[1])) {
        if ((pt[0]==eR)&&(pt[1]+1==eC)) {
          MazePoint finalPoint = new MazePoint(pt[0], pt[1]+1, pt[2]+1);
          finalPoint.setPath(point.path());
          finalPoint.addPointToPath(finalPoint);
          this.shortestPath = finalPoint.path();
          return pt[2]+1;
        }
        else {
          visited[pt[0]][pt[1]+1] = true;
          MazePoint nextpt = new MazePoint(pt[0], pt[1]+1, pt[2]+1);
          nextpt.setPath(point.path());
          nextpt.addPointToPath(nextpt);
          //int[] nextpt = {pt[0], pt[1]+1, pt[2]+1};
          q.add(nextpt);
        }
      }
    }
    return 0;
  }
  public void printShortestPath() {
    if (this.shortestPath == null) return;
    for (int i=0; i<this.shortestPath.size(); ++i) {
      MazePoint newPt = this.shortestPath.get(i);
      System.out.println("Point " + newPt.getDist() + 
          " at X=" + newPt.getX() + ", Y=" + newPt.getY());
    }
  }

  public static void main (String[] args) {
    int[][] maze = new int[][]{
      {0,1,1,1,1,1,0,0,1,1},
      {1,1,0,1,1,1,1,1,1,1},
      {0,1,0,1,1,0,0,1,0,0},
      {1,1,1,0,0,0,1,1,0,1},
      {1,0,0,1,1,1,1,1,1,1},
      {1,0,0,1,1,0,1,0,0,1},
      {1,1,1,1,0,1,1,1,1,1},
      {0,1,0,1,0,1,0,1,1,1},
      {0,1,0,0,1,1,1,0,0,0},
      {1,1,1,1,1,1,1,0,0,1}
    };
    MazeSearch newSearch = new MazeSearch(maze);
    System.out.println(newSearch.pathExists(9,0,0,9));
    System.out.println(newSearch.bfs(9,0,0,9));
    newSearch.printShortestPath();
  }
}

@SuppressWarnings("unchecked")
class MazePoint {
  private int x;
  private int y;
  private int dist;
  private ArrayList<MazePoint> path;

  public MazePoint(int x, int y, int dist) {
    this.x = x;
    this.y = y;
    this.dist = dist;
  }

  public void setPath(ArrayList<MazePoint> path) {
    this.path = path;
  }
  public void addPointToPath(MazePoint mp) {
    this.path.add(mp);
  }
  public ArrayList<MazePoint> path() {
    return this.path;
  }
  public int[] getPointArray() {
    int[] pointArray = {this.x, this.y, this.dist};
    return pointArray;
  }
  public void setX(int x) { this.x = x; }
  public void setY(int y) { this.y = x; }
  public void setDist(int dist) { this.dist = dist; }
  public int getX() { return this.x; }
  public int getY() { return this.y; }
  public int getDist() { return this.dist; }

}
