import java.util.Random;
public class IntegerQuicksort {
  public static void swap(int[] A, int i0, int i1) {
    int tmp = A[i0];
    A[i0] = A[i1];
    A[i1] = tmp;
  }
  public static int choosePivot(int lo, int hi) {
    Random rand = new Random();
    int randNum = rand.nextInt((hi-lo)+1) + lo;
    return randNum;
  }
  public static void quicksort(int[] A, int lo, int hi) {
    if(lo<hi) {
      int p = partition(A, lo, hi);
      quicksort(A, lo, p-1);
      quicksort(A, p+1, hi);
    }
  }
  public static int partition(int[] A, int lo, int hi) {
    int pivotIdx = choosePivot(lo, hi);
    int pivotVal = A[pivotIdx];
    swap(A, pivotIdx, hi);
    int storeIdx = lo;
    for(int i=lo; i<hi; ++i) {
      if(A[i] < pivotVal) {
        swap(A, i, storeIdx);
        storeIdx++;
      }
    }
    swap(A, storeIdx, hi);
    return storeIdx;
  }
  public static void printArray(int[] arr) {
    System.out.print("[");
    for(int i = 0; i < arr.length; ++i) {
      if(i>0) System.out.print(", ");
      System.out.print(arr[i]);
    }
    System.out.print("]");
  }

  public static void main(String[] args) {
    int[] uArray = new int[]{6,2,8,4,5,1,9,3,5,7,0};
    printArray(uArray);
    System.out.println();
    quicksort(uArray, 0, uArray.length-1);
    printArray(uArray);
  }
} 
