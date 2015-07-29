/**
 * @author      Leejay Schmidt <leejay@leejayschmidt.com>
 * @version     1.0
 * @since       2015-07-29 
*/

public class MutableInteger extends Number implements Comparable<MutableInteger> {
  //locking mechanism to prevent changes to number is this is desired
  private boolean locked;
  //the integer value that this is wrapped around
  private int value;
  public final static int MAX_VALUE = ~0;
  public final static int MIN_VALUE = -(~0);
  /**
   * Constructor with no parameters, initializes with value of 0
   * @return New MutableInteger object initilized to 0
   */
  public MutableInteger() {
    super();
    this.value = 0;
    locked = false;
  }
  /**
   * Constructor that initializes with int value passed
   *
   * @param value int value to initialize with
   * @return New MutableInteger object 
   */
  public MutableInteger(int value) {
    super();
    this.value = value;
    locked = false;
  }
  /**
   * Constructor that initializes with Integer value passed
   *
   * @param value Integer value to initialize with
   * @return New MutableInteger object 
   */
  public MutableInteger(Integer value) {
    super();
    this.value = value.intValue();
    locked = false;
  }
 /**
   * Constructor that initializes with string value passed
   *
   * @param str String of integer value to initialize with
   * @return New MutableInteger object, with integer parsed from string 
   */
  public MutableInteger(String str) {
    super();
    this.value = strToInt(str);
    locked = false;
  }
  /**
   * Engages the locking mechanism to prevent changes in value
   */ 
  public void lock() {
    this.locked = true;
  }
  /**
   * Disengages the locking mechanism, allowing changes in value
   */ 
  public void unlock() {
    this.locked = false;
  }
  /**
   * Checks whether the lock is engaged
   * @return The current state of the lock (true if locked, false otherwise)
   */ 
  public boolean isLocked() {
    return this.locked;
  }
  /**
   * Increments the value by 1 if not locked
   * @return true if unlocked and successful, false otherwise
   */ 
  public boolean increment() {
    if(!locked) {
      ++this.value;
      return true;
    }
    return false;
  }
  /**
   * Decrements the value by 1 if not locked
   * @return true if unlocked and successful, false otherwise
   */ 
  public boolean decrement() {
    if(!locked) {
      --this.value;
      return true;
    }
    return false;
  }
  /**
   * Adds the given int value to the current value if not locked
   * @param n The int value to add to the current value
   * @return true if unlocked and successful, false otherwise
   */ 
  public boolean add(int n) {
    if(!locked) {
      this.value += n;
      return true;
    }
    return false;
  }
  /**
   * Subtracts the given int value from the current value if not locked
   * @param n The int value to subtract from the current value
   * @return true if unlocked and successful, false otherwise
   */ 
  public boolean subtract(int n) {
    if(!locked) {
      this.value -= n;
      return true;
    }
    return false;
  }
  /**
   * Multiplies the current value by the given int value if not locked
   * @param n The int value to multiply the current value by
   * @return true if unlocked and successful, false otherwise
   */ 
  public boolean multiply(int n) {
    if(!locked) {
      this.value *= n;
      return true;
    }
    return false;
  }
  /**
   * Divides the current value by the given int value if not locked
   * @param n The int value to divide the current value by
   * @return true if unlocked and successful, false otherwise
   */ 
  public boolean divide(int n) {
    if(!locked) {
      this.value /= n;
      return true;
    }
    return false;
  }
  /**
   * Compares with given MutableInteger object
   * @see java.lang.Comparable
   * @param obj The MutableInteger object to compare with
   * @return 1 if greater than given object, 0 if equal, -1 if less
   */ 
  @Override
  public int compareTo(MutableInteger obj) {
    if(this.value==obj.value()) {
      return 0;
    }
    return this.value>obj.value()?1:-1;
  }
  /**
   * Sees if the value of given MutableInteger object is equal to current value
   * @param obj The MutableInteger object to compare with
   * @return true if equal, false if not
   */ 
  public boolean equals(MutableInteger obj) {      
    return this.value==obj.value();
  }
  /**
   * Sets the current value of the MutableInteger to the given value if not
   * locked
   * @param value The int value to set the MutableInteger value to
   * @return true if unlocked and successful, false otherwise
   */
  public boolean setValue(int value) {
    if(!this.locked) {
      this.value = value;
      return true;
    }
    return false;
  }
  /**
   * Parses a string to the equivalent integer value
   * @param str The string to parse
   * @return The int value of the string
   */ 
  public static int strToInt(String str) {
    char[] chars = str.toCharArray();
    int val = 0, sign = 1;
    for(int i = 0; i < chars.length; ++i) {
      if(i==0&&chars[i]=='-') sign = -1;
      else {
        int tmp = (int)chars[i];
        if(tmp>=0 && tmp<10) {
          val*=10;
          val+=tmp;
        }
        else return val;
      }
    }
    return sign*val;
  }
  /**
   * Gives the byte value of the current MutableInteger value
   * @see java.lang.Number
   * @return byte value of the current value
   */
  @Override
  public byte byteValue() {
    return (byte)this.value;
  }
  /**
   * Gives the double value of the current MutableInteger value
   * @see java.lang.Number
   * @return double value of the current value
   */ 
  @Override
  public double doubleValue() {
    return (double)this.value;
  }
  /**
   * Gives the float value of the current MutableInteger value
   * @see java.lang.Number
   * @return float value of the current value
   */
  @Override
  public float floatValue() {
    return (float)this.value;
  }
  /**
   * Gives the int value of the current MutableInteger value
   * @see java.lang.Number
   * @return int value of the current value
   */
  @Override
  public int intValue() {
    return this.value;
  }
  /**
   * Gives the long value of the current MutableInteger value
   * @see java.lang.Number
   * @return long value of the current value
   */
  @Override
  public long longValue() {
    return (long)this.value;
  }
  /**
   * Gives the short value of the current MutableInteger value
   * @see java.lang.Number
   * @return short value of the current value
   */
  @Override
  public short shortValue() {
    return (short)this.value;
  }
  /**
   * Gives the current int value of the MutableInteger
   * @return int value currently stored
   */
  public int value() {
    return this.value;
  }
}



