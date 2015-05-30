// file: HuffmanC.java
// author: Bob Muller
// date: March 10, 2012
//
// The HuffmanTreeC class implements Huffman trees. The static method
// makeHuffmanCodingTree produces a HuffmanTree from an input symbol
// table.
//
import java.util.*;

public class HuffmanTreeC implements HuffmanTree, Comparable {

    private int weight;
    private char ch;
    private HuffmanTree left;
    private HuffmanTree right;

    public HuffmanTreeC(int weight, char ch) {
 this.weight = weight;
 this.ch = ch;
    }

    public HuffmanTreeC(int weight, char ch, HuffmanTree left, HuffmanTree right) {
 this.weight = weight;
 this.ch = ch;
 this.left = left;
 this.right = right;
    }

    public static HuffmanTree makeHuffmanCodingTree(SymbolTable st) {

 PriorityQueue<HuffmanTree> pq = new PriorityQueue<HuffmanTree>();

 // Insert the initial entries in the priority queue.
 //
 Set<Integer> keys = st.keySet();
 for(Integer i : keys) {
     STValue v = (STValue) st.get(i);
     int frequency = v.getFrequency();
     pq.add(new HuffmanTreeC(frequency, (char) i.intValue()));
 }

 // Now synthesize the Huffman Coding tree by boiling down the priority
 // queue.
 //
 while(pq.size() > 1) {
     
     HuffmanTree t1 = pq.poll();
     HuffmanTree t2 = pq.poll();
     
     int weight = t1.getWeight() + t2.getWeight();
     
     pq.add(new HuffmanTreeC(weight, (char) 0, t1, t2));
 }
 return pq.poll();
    }

    public boolean isLeaf() { return this.getChar() != 0; }
    public int getWeight() { return this.weight; }
    public char getChar() { return this.ch; }
    public HuffmanTree getLeft() { return this.left; }
    public HuffmanTree getRight() { return this.right; }

    public String toString() {

 if(this.ch != 0) return this.ch + ":" + this.weight;

 String ls = this.left != null ? this.left.toString() : "";
 String rs = this.right != null ? this.right.toString() : "";
 return "HT(" + this.weight + ", " + ls + ", " + rs + ")";
    }

    public int compareTo(Object o) {
 HuffmanTree other = (HuffmanTree) o;
 return this.getWeight() - other.getWeight();
    }
}

