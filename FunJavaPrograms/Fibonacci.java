public class Fibonacci {
  //Recursive Solution
  //Worst case: O(n!)
  //Best case: O(1)
  public static int slowfib(int n) {
    return n<2 ? n : slowfib(n-2)+slowfib(n-1);
  }
  //Dynamic Programming Solution
  //Worst Case: O(n)
  //Best case: O(1)
  public static int fastfib(int n) {
    if(n<2) return n;
    else {
      int fibMinus2 = 0;
      int fibMinus1 = 1;
      int fib = 0;
      for(int i=2; i<=n; ++i) {
        fib = fibMinus2+fibMinus1;
        fibMinus2 = fibMinus1;
        fibMinus1 = fib;
      }
      return fib;
    }
  }
  public static void main(String[] args) {
    long startTime = System.nanoTime();
    System.out.println(slowfib(35));
    System.out.println("Runtime: " + ((System.nanoTime()-startTime)/1000000));
    startTime = System.nanoTime();
    System.out.println(fastfib(35));
    System.out.println("Runtime: " + ((System.nanoTime()-startTime)/1000000));
  }
}

