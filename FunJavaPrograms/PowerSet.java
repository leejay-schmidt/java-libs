import java.util.*;

public class PowerSet
{
  public static Set<Set<Integer>> powerset(Set<Integer> set) {
    Set<Set<Integer>> ps = new HashSet<Set<Integer>>();
    if(set.isEmpty()) {
      Set<Integer> emptySet = new HashSet<Integer>();
      ps.add(emptySet);
      return ps;
    }
    List<Integer> list = new ArrayList<Integer>(set);
    Integer head = list.get(0);
    Set<Integer> rest = new HashSet<Integer>(list.subList(1, list.size()));
    for(Set<Integer> s: powerset(rest)) {
      Set<Integer> newSet = new HashSet<Integer>();
      newSet.add(head);
      newSet.addAll(s);
      ps.add(newSet);
      ps.add(s);
    }
    return ps;
  }

  public static void main(String[] args) {
    Set<Integer> sampleSet = new HashSet<Integer>();
    sampleSet.add(new Integer(5));
    sampleSet.add(new Integer(4));
    sampleSet.add(new Integer(3));
    sampleSet.add(new Integer(2));
    sampleSet.add(new Integer(1));

    System.out.println(sampleSet.toString());
    System.out.println(powerset(sampleSet).toString());
  }


}
