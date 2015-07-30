public class IntArrayCycleFinder {
  public static int findCycle(int[] cyc) {
    int bottom=0, top=cyc.length-1;
    return findCycleHelper(cyc, bottom, top);
  }
  private static int findCycleHelper(int[] cyc, int bottom, int top) {
    int middle = (top+bottom)/2;
    if(bottom==top||cyc[top]>cyc[bottom]) {
      return bottom;
    }
    if(cyc[middle]<cyc[bottom]) {
      return findCycleHelper(cyc, bottom, middle+((bottom+top)%2));
    }
    return findCycleHelper(cyc, middle+((bottom+top)%2), top);
  }
  public static void main(String[] args) {
    int[] cyc1 = new int[]{378, 478, 550, 631,
      103, 203, 220, 234, 279, 368};
    int[] cyc2 = new int[]{378, 478, 550, 631,
      103, 203, 220, 234, 279, 368, 369};
    int[] cyc3 = new int[]{279, 368, 369, 378, 478,
      550, 552, 631, 650, 103, 203, 220, 234, 245,
      250};
    System.out.println(findCycle(cyc1));
    System.out.println(findCycle(cyc2));
    System.out.println(findCycle(cyc3));
  }
}

