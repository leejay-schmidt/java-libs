/**
 * @author      Leejay Schmidt <leejay@leejayschmidt.com>
 * @version     1.0
 * @since       2015-07-30 
*/
public class Pair<T extends Comparable<T>,U> implements Comparable<Pair<T,U>> {
  //first item in the pair
  private T first;
  //second item in the pair
  private U second;
  /**
    * Constructor for the pair, with provided items
    * @param firstItem The first (generic) item in the pair
    * @param secondItem The second (generic) item in the pair
    */
  public Pair(T firstItem, U secondItem) {
    this.first = firstItem;
    this.second = secondItem;
  }
  /**
    * Constructor for the pair, with null items
    */
  public Pair() {
    this.first = null;
    this.second = null;
  }
  /**
    * Getter for the first item in the pair
    * @return The first item in the pair
    */
  public T first() {
    return this.first;
  }
  /**
    * Getter for the second item in the pair
    * @return The second item in the pair
    */
  public U second() {
    return this.second;
  }
  /**
    * Setter for the first item in the pair
    * @param first The item to set the first item of the pair to
    */
  public void setFirst(T first) {
    this.first = first;
  }
  /**
    * Setter for the second item in the pair
    * @param second The item to set the second item of the pair to
    */
  public void setSecond(U second) {
    this.second = second;
  }
  @Override
  public int compareTo(Pair<T,U> p2) {
    return first.compareTo(p2.first);
  }
}
