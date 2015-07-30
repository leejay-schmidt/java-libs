public class IntegerMergeSort {
  public static void MergeSort(int[] A, int[] B, int n) {
    MergeSplit(A, 0, n, B);
  }
  public static void MergeSplit(int[] A, int iBegin, int iEnd, int[] B) {
    if(iEnd-iBegin < 2) return; //assume sorted
    int iMiddle = (iBegin + iEnd) / 2; //split in half
    MergeSplit(A, iBegin, iMiddle, B);
    MergeSplit(A, iMiddle, iEnd, B);
    Merge(B, iBegin, iMiddle, iEnd, A);
    ArrayCopy(B, iBegin, iEnd, A);
  }
  public static void Merge(int[] B, int iBegin, int iMiddle, int iEnd, int[] A) {
    int i0 = iBegin, i1 = iMiddle;
    for(int j = iBegin; j < iEnd; ++j) {
      if(i0 < iMiddle && (i1 >= iEnd || A[i0] <= A[i1]))
        B[j] = A[i0++];
      else
        B[j] = A[i1++];
    }
  }
  public static void ArrayCopy(int[] B, int iBegin, int iEnd, int[] A) {
    for(int k = iBegin; k < iEnd; ++k)
      A[k] = B[k];
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
    int[] nArray = new int[uArray.length];
    System.out.println();
    MergeSort(uArray, nArray, uArray.length);
    printArray(uArray);
  }
}

