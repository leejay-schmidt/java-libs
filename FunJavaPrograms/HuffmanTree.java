import java.util.*;

public class HuffmanTree {
  Node root;
  List<Pair<String,String>> book;
  String encodedMessage;

  public HuffmanTree(String message) {
    char[] messageChars = message.toCharArray();
    int[] tmpFrequencyTable = new int[128];
    for(char i:messageChars) {
      tmpFrequencyTable[(int)i]++;
    }
    int numChars = 0;
    List<Integer> freqList = new ArrayList<>();
    List<String> strList = new ArrayList<String>();
    for(int i=0; i<tmpFrequencyTable.length; ++i) {
      if(tmpFrequencyTable[i]>0) {
        freqList.add(tmpFrequencyTable[i]);
        String s = "" + (char)i;
        strList.add(s);
      }
    }
    int[] frequencyTable = new int[freqList.size()];
    int j = 0;
    for(Integer i:freqList) {
      frequencyTable[j++] = i.intValue();
    }
    //frequencyTable = freqList.toArray(frequencyTable);
    String[] text = new String[strList.size()];
    text = strList.toArray(text);
    this.makeHuffmanTree(frequencyTable, text);
    this.codeBook();
    Map<String,Pair<String,String>> codeMap = new HashMap<>();
    for(Pair<String,String> i : this.book) {
      codeMap.put(i.first, i);
    }
    StringBuffer sb = new StringBuffer();
    for(char c : messageChars) {
      String s = "" + c;
      Pair<String,String> charVal = codeMap.get(s);
      sb.append(charVal.second);
    }
    encodedMessage = sb.toString();
  }

  public HuffmanTree(int[] frequencies, String[] text) {
    this.makeHuffmanTree(frequencies, text);
    this.codeBook();
    this.encodedMessage = "";
  }

  public void makeHuffmanTree(int[] frequencies, String[] text) {
    PriorityQueue<Node> queue = new PriorityQueue<Node>();
    for(int i=0; i<text.length; ++i) {
      Node n = new Node(text[i], frequencies[i]);
      queue.add(n);
    }
    Node treeroot = null;
    while(queue.size() > 1) {
      Node least1 = queue.poll();
      Node least2 = queue.poll();
      Node combined = new Node(least1.frequency+least2.frequency);
      combined.right = least1;
      combined.left = least2;
      least1.parent = combined;
      least2.parent = combined;
      queue.add(combined);
      treeroot = combined;
    }
    this.root = treeroot;
  }

  public String decodeHuffmanTree() {
    Map<String,Pair<String,String>> codeMap = new HashMap<>();
    for(Pair<String,String> i: book) {
      codeMap.put(i.second, i);
    }
    char[] encMessageChar = encodedMessage.toCharArray();
    String s = "";
    StringBuffer bf = new StringBuffer();
    for(char c:encMessageChar) {
      s = s+c;
      Pair<String,String> charStr = codeMap.get(s);
      if(charStr!=null) {
        bf.append(charStr.first); 
        s = "";
      }
    }
    return bf.toString();
  }
  
  public List<Pair<String, String>> codeBook() {
    book = new ArrayList<Pair<String,String>>();
    genCodeBook(root, "");
    Collections.sort(book); 
    return book;
  }

  public void printCodeBook() {
    if(null==book) {
      return;
    }
    for(Pair<String,String> pair : book) {
      System.out.println("[ '"+pair.first+"':"+pair.second+" ]");
    }
  }

  private void genCodeBook(Node node, String code) {
    if(node.left==null&&node.right==null) {
      if(code.equals("")) code=code+"0";
      book.add(new Pair<String,String>(node.text, code));
    }
    else {
      if(node.left!=null) genCodeBook(node.left, code+"1");
      if(node.right!=null) genCodeBook(node.right, code+"0");
    }
  }

  public static void main(String[] args) {
    int frequencies[] = {817, 149, 278, 425, 1270, 223, 202, 609, 697,
      15, 77, 403, 241, 675, 751, 193, 10, 599, 633, 906, 276, 98, 236,
      15, 197, 7};
    String text[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
      "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
      "z"};
    HuffmanTree newHuff = new HuffmanTree(frequencies, text);
    newHuff.printCodeBook();
    System.out.println("\n\n\n\n\n");
    HuffmanTree msgHuff = new HuffmanTree("The quick red fox jumped over the" +
        " lazy brown dog");
    msgHuff.printCodeBook();
    System.out.println(msgHuff.encodedMessage);
    System.out.println(msgHuff.decodeHuffmanTree());
  }

}

class Pair<T extends Comparable<T>,U> implements Comparable<Pair<T,U>> {
  T first;
  U second;

  public Pair(T firstItem, U secondItem) {
    this.first = firstItem;
    this.second = secondItem;
  }

  @Override
  public int compareTo(Pair<T,U> p2) {
    return first.compareTo(p2.first);
  }
}

class Node implements Comparable<Node> {
  Node left;
  Node right;
  Node parent;
  String text;
  int frequency;

  public Node(String text, int frequency) {
    this.text = text;
    this.frequency = frequency;
  }

  public Node(int frequency) {
    this.text = "";
    this.frequency = frequency;
  }

  public int compareTo(Node n) {
    if(frequency < n.frequency) {
      return -1;
    }
    else if(frequency > n.frequency) {
      return 1;
    }
    return 0;
  }

  public static Node makeHuffmanTree(int frequencies[], String[] text) {
    PriorityQueue<Node> queue = new PriorityQueue<Node>();
    for(int i=0; i<text.length; ++i) {
      Node n = new Node(text[i], frequencies[i]);
      queue.add(n);
    }
    Node root = null;
    while(queue.size() > 1) {
      Node least1 = queue.poll();
      Node least2 = queue.poll();
      Node combined = new Node(least1.frequency+least2.frequency);
      combined.right = least1;
      combined.left = least2;
      least1.parent = combined;
      least2.parent = combined;
      queue.add(combined);
      root = combined;
    }
    return root;
  }

}
