public class SpecialTree {
  public static int getIndex(BinaryNode<Integer> root, int value) {
    MutableInteger idx = new MutableInteger(-1);
    Integer val = new Integer(value);
    InOrderNoOutput(root, val, idx);
    return idx.isLocked()?idx.value():-1;
  }
  public static void InOrderNoOutput(BinaryNode<Integer> root, Integer val,
      MutableInteger idx) {
    if(idx.isLocked()) { //rapid termination to prevent extra operations
      return;
    }
    if(root.left() != null) {
      InOrderNoOutput(root.left(), val, idx);
    }
    if(!idx.incVal()) {
      return;
    }
    if(root.data().equals(val)) {
      idx.lock();
      return;
    }
    if(root.right() != null) {
      InOrderNoOutput(root.right(), val, idx);
    }
  }
  public static void main(String[] args) {
    BinaryNode<Integer> node0 = new BinaryNode<Integer>(new Integer(5));
    BinaryNode<Integer> node1 = new BinaryNode<Integer>(new Integer(3));
    BinaryNode<Integer> node2 = new BinaryNode<Integer>(new Integer(7));
    node1.setLeft(node0);
    node1.setRight(node2);
    BinaryNode<Integer> node3 = new BinaryNode<Integer>(new Integer(2));
    node3.setLeft(node1);
    BinaryNode<Integer> node4 = new BinaryNode<Integer>(new Integer(8));
    BinaryNode<Integer> node5 = new BinaryNode<Integer>(new Integer(4));
    BinaryNode<Integer> node6 = new BinaryNode<Integer>(new Integer(9));
    node5.setLeft(node4);
    node5.setRight(node6);
    node3.setRight(node5);
    System.out.println(getIndex(node3, 7) + ": 2");
    System.out.println(getIndex(node3, 8) + ": 4");
    System.out.println(getIndex(node3, 2) + ": 3");
    System.out.println(getIndex(node3, 9) + ": 6");
    System.out.println(getIndex(node3, 4) + ": 5");
  }


}

@SuppressWarnings("unchecked")
class BinaryNode<T extends Comparable<? super T>> {
  private T data;
  private BinaryNode<T> left;
  private BinaryNode<T> right;
  public BinaryNode() {
    data = null;
    left = null;
    right = null;
  }
  public BinaryNode(T dat) {
    this.data = dat;
    left = null;
    right = null;
  }
  public int compareTo(BinaryNode<T> node) {
    return this.data().compareTo(node.data());
  }
  public void setRight(BinaryNode<T> node) {
    this.right = node;
  }
  public void setLeft(BinaryNode<T> node) {
    this.left = node;
  }
  public BinaryNode<T> left() {
    return this.left;
  }
  public BinaryNode<T> right() {
    return this.right;
  }
  public void setData(T dat) {
    this.data = dat;
  }
  public T data() {
    return this.data;
  }
}

@SuppressWarnings("unchecked")
class MutableInteger {
  private int value;
  private boolean locked;
  public MutableInteger() {
    value = 0;
    locked = false;
  }
  public MutableInteger(int n) {
    value = n;
    locked = false;
  }
  public void lock() {
    this.locked = true;
  }
  public void unlock() {
    this.locked = false;
  }
  public boolean isLocked() {
    return this.locked;
  }
  public boolean setVal(int val) {
    if(!locked) {
      value = val;
      return true;
    }
    return false;
  }
  public boolean incVal() {
    if(!locked) {
      ++value;
      return true;
    }
    return false;
  }
  public int value() {
    return value;
  }
}
