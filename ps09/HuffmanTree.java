public interface HuffmanTree {
    public int getWeight();
    public char getChar();
    public HuffmanTree getLeft();
    public HuffmanTree getRight();
    public boolean isLeaf();
    public String toString();
    public int compareTo(Object o);
}