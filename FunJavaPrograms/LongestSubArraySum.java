import java.util.*;

public class LongestSubArraySum {
  
  static class Pair<T,U> {
    private T firstItem;
    private U secondItem;

    public Pair() {
      firstItem = null;
      secondItem = null;
    }
    public Pair(T firstItem, U secondItem) {
      this.firstItem = firstItem;
      this.secondItem = secondItem;
    }
    public T getFirst() { return this.firstItem; }
    public U getSecond() { return this.secondItem; }
    public void setFirst(T first) { this.firstItem = first; }
    public void setSecond(U second) { this.secondItem = second; }
  }


  public static int[] longestSum(int[] A, int k) {
    int n = A.length;
    int[] len = new int[n];
    int[] tuple = new int[]{-1, -1};
    int curLongest = -1;
    for(int i=0; i<n; ++i) {
      int sum = 0;
      int greatestIdx = i-1;
      for(int j=i; j<n; ++j) {
        sum+=A[j];
        if(sum<=k) {
          greatestIdx=j;
        }
      }
      len[i] = greatestIdx - i;
    }
    for(int j=0; j<n; ++j) {
      if(len[j]>curLongest) {
        curLongest=len[j];
        tuple=new int[]{j, j+curLongest};
      }
    }
    return tuple;
  }
  public static Pair<Integer, Integer> getLongestSubArray(
      List<Integer> A, int k) {
    List<Integer> prefixSum = new ArrayList<>();
    int sum = 0;
    for(int a : A) {
      sum+=a;
      prefixSum.add(sum);
    }
    if(prefixSum.get(A.size()-1) <= k) {
      return new Pair<>(0, A.size()-1);
    }
    List<Integer> minPrefixSum = new ArrayList<>(prefixSum);
    for(int i=minPrefixSum.size()-2; i>=0; --i) {
      minPrefixSum.set(i, Math.min(minPrefixSum.get(i), minPrefixSum.get(i+1)));
    }
    Pair<Integer, Integer> idx = new Pair<>(-1, -1);
    int a=0, b=0, max_len=0;
    while(a < A.size() && b < A.size()) {
      int minCurrSum =
        a>0 ? minPrefixSum.get(b)-prefixSum.get(a-1) : minPrefixSum.get(b);
      if(minCurrSum <= k) {
        int currLength = b - a + 1;
        if(currLength > max_len) {
          max_len = currLength;
          idx = new Pair<>(a,b);
        }
        ++b;
      }
      else {
        ++a;
      }
    }
    return idx;
  }
 

  public static void main(String[] args) {    
    //int[] A = {431, -15, 639, 342, -14, 565, -924, 635, 167, -70};
    //List<Integer> AL = new ArrayList<>();
    //AL.add(431);
    //AL.add(-15);
    //AL.add(639);
    //AL.add(342);
    //AL.add(-14);
    //AL.add(565);
    //AL.add(-924);
    //AL.add(635);
    //AL.add(167);
    //AL.add(-70);
    int[] A = new int[100000];
    List<Integer> AL = new ArrayList<>();
    Random random = new Random();
    for(int i=0; i<100000; ++i) {
      int randNum = (random.nextInt(1000)-35);
      A[i] = randNum;
      AL.add(new Integer(randNum));
    }
    final long startTime1 = System.nanoTime();
    int[] longest = longestSum(A, 180);
    System.out.println("Longest Sum:: A["+longest[0]+":"+longest[1]+"]");
    final long duration1 = System.nanoTime() - startTime1;
    System.out.println(duration1);
    final long startTime2 = System.nanoTime();
    Pair<Integer,Integer> longestpair = getLongestSubArray(AL, 180);
    System.out.println("Longest Sum:: A["+longestpair.getFirst()+":"+
        longestpair.getSecond()+"]");
    final long duration2 = System.nanoTime() - startTime2;
    System.out.println(duration2);
  }
}
