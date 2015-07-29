public class MutableInteger {
  private int value;
  private boolean locked;
  public static final int MAX_VALUE = ~0;
  public static final int MIN_VALUE = -(~0);
  public MutableInteger() {
    locked = false;
    value = 0;
  }
  public MutableInteger(int value) {
    locked = false;
    this.value = value;
  }


