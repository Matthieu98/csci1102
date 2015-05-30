// file: STValue.java
// author: Bob Muller
// date: March 5, 2012
//
// This file contains a class representing values in a symbol table
// for a Huffman coding/decoding algorithm.  A symbol table value
// packages up an integer frequency as well as the binary address of
// the symbol in the Huffman coding tree.
//
public interface STValue {

    public int getFrequency();
    public BitString getBitString();

    public void setFrequency(int frequency);
    public void setBitString(BitString bs);
    public String toString();
}