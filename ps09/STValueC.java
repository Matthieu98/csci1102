// file: STValue.java
// author: Bob Muller
// date: March 5, 2012
//
// This file contains a class representing values in a symbol table
// for a Huffman coding/decoding algorithm.  A symbol table value
// packages up an integer frequency as well as the binary address of
// the symbol in the Huffman coding tree.
//
public class STValueC implements STValue {

    private int frequency;
    private BitString bitString;

    public STValueC(int frequency, BitString bitString) {
 this.frequency = frequency;
 this.bitString = bitString;
    }

    public STValueC(int frequency) {
 this.frequency = frequency;
 this.bitString = null;
    }

    public int getFrequency() { return this.frequency; }
    public void setFrequency(int frequency) { this.frequency = frequency; }

    public BitString getBitString() { return this.bitString; }
    public void setBitString(BitString bs) { this.bitString = bs; }

    public String toString() {
 String bs = this.bitString == null ? "" : this.bitString.toString();
 return "{frequency = " + this.frequency + ", bitstring = " + bs + "}";
    }
}