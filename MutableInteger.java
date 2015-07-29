public class MutableInteger extends Number implements Comparable<MutableInteger>{
  private boolean locked;
  private int value;
  public MutableInteger() {
    super();
    locked = false;
  }
  public MutableInteger(int value) {
    super();
    this.value = value;
    locked = false;
  }
  public MutableInteger(Integer value) {
    super();
    this.value = value.intValue();
    locked = false;
  }
  public MutableInteger(String str) {
    super();
    this.value = strToInt(str);
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
  public boolean increment() {
    if(!locked) {
      ++this.value;
      return true;
    }
    return false;
  }
  public boolean decrement() {
    if(!locked) {
      --this.value;
      return true;
    }
    return false;
  }
  public boolean add(int n) {
    if(!locked) {
      this.value += n;
      return true;
    }
    return false;
  }
  public boolean subtract(int n) {
    if(!locked) {
      this.value -= n;
      return true;
    }
    return false;
  }
  public boolean multiply(int n) {
    if(!locked) {
      this.value *= n;
      return true;
    }
    return false;
  }
  public boolean divide(int n) {
    if(!locked) {
      this.value /= n;
      return true;
    }
    return false;
  }
  @Override
  public int compareTo(MutableInteger obj) {
    if(this.value==obj.value()) {
      return 0;
    }
    return this.value>obj.value()?1:-1;
  }
  public boolean equals(MutableInteger obj) {      
    return this.value==obj.value();
  }
  public boolean setValue(int value) {
    if(!this.locked) {
      this.value = value;
      return true;
    }
    return false;
  }
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
  @Override
  public byte byteValue() {
    return (byte)this.value;
  }
  @Override
  public double doubleValue() {
    return (double)this.value;
  }
  @Override
  public float floatValue() {
    return (float)this.value;
  }
  @Override
  public int intValue() {
    return this.value;
  }
  @Override
  public long longValue() {
    return (long)this.value;
  }
  @Override
  public short shortValue() {
    return (short)this.value;
  }
  public int value() {
    return this.value;
  }
}



