
class Obj { @Override public String toString() {return "DUMMY";} }

public class HSet <T> implements ISet <T>, Iterable <T> {

  private static final Object DUMMY = new Obj(); // new Object();
  private static final int DEFAULT_CAPACITY = 3;
  private static final double DEFAULT_LOAD_FACTOR = 0.75;

  private Mapping <T, Object> map;

  public HSet () {
    this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  public HSet(int capacity) {
    this(capacity, DEFAULT_LOAD_FACTOR);
  }

  public HSet(int capacity, double load_factor) {
    map = new Mapping<>(capacity, load_factor);
  }

  public int size() {
    return map.size();
  }

  public void clear() {
    map.clear();
  }

  public boolean isEmpty() {
    return map.isEmpty();
  }

  public boolean add(T elem) {
    return map.add(elem, DUMMY) == DUMMY;
  }

  public boolean remove(T elem) {
    return map.remove(elem) == DUMMY;
  }

  public boolean contains(T elem) {
    return map.hasKey(elem);
  }

  public java.util.Iterator <T> iterator() {
    return map.iterator();
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (T key : map) sb.append(key + ", ");
    sb.append("]");
    return sb.toString();
  }

} 

