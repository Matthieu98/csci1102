// file: Huff.java
// author: Bob Muller
// date: February 23, 2012
//
// This is the Huffman compression half of the Huffman compression/decompression
// algorithm. (This is sometimes called a codec for "code"/"decode".) The algorithm
// implemented here accepts a single command line argument that should be a filename
// of the form name.txt, where the extension ".txt" is required. It then proceeds
// as follows:
// 
// 1. Read all of the characters from the input file and create a symbol table that
//    records the frequency of each character that appears in the input file.
//
// 2. Use the constructed symbol table as the basis for constructing the corresponding
//    Huffman coding tree.
//
// 3. Using the Huffman coding tree, fill in the symbol table entry for each character
//    to record the bit string assigned to that character in the Huffman coding tree.
//
// 4. Create the compressed file.
//    A. Write out the 2-byte bit string 0x00BC that identifies the file as one that is 
//    compressed by our algorithm.
//    B. Write out the 4-byte int size of the frequency table.
//    C. Write out the frequency table. Each entry in the table is a pair of values
//       occupying a total of 5 bytes. In particular, with 1 byte for the character code
//       followed by the 4-byte int giving the character's frequency in the input file.
//    D. Now the symbol table can be used to look up the bit strings representing each
//       character. Read through the input file one more time, for each occurrence of
//       a character in the input file, write out its bit string to the binary output file.
//
//    CONVENTIONS: In addition to the above binary file format, we require that:
//      1. When a Huffman tree with weight w is inserted into the priority queue, if there
//         are already trees in the queue with the same weight, the one being inserted should
//         be put BEHIND all others of the same weight;
//      2. When bit patterns are being assigned to characters, the bit strings are built-up
//         by adding bits to the RIGHT (rather than the left) and a leftward branch in the
//         tree is assigned a 0 bit and a rightward branch is assigned a 1 bit.
//
//       
// Structure of zip file:
//
// File  ::= 2-BYTE-ID 4-BYTE-TABLESIZE TABLE BINARYCODE
// TABLEENTRY ::= 1-BYTE-SYMBOL 4-BYTE-INT-FREQUENCY
//

import java.io.*;
import java.util.*;

public class Huff {

    // The following creates a hexadecimal (base 16) constant.
    //
    public static final int MAGIC_NUMBER = 0x00BC;
    public static final boolean DEBUG = true;

    private final String[] args;

    public Huff(String[] args) {

 this.args = args;
    }

    // This is the main routine in the Huffman Coding Algorithm.
    //
    private void go() {

 FileIO io = new FileIOC();

 FileReader inputFile = io.openInputFile(this.args[0]);

 if (DEBUG) 
     System.out.println("go: opened input file " + this.args[0]);
 
 SymbolTable<Integer, STValue> st = new SymbolTableC<Integer, STValue>();

 int c = 0;

 try {
     while(c != -1) {
  c = inputFile.read();           // NB: read returns -1 at EOF.
  if(c != -1) {
      if(st.containsKey(c)) {
   STValue v = st.get(c);
   v.setFrequency(v.getFrequency() + 1);
      }
      else
   st.put(c, new STValueC(1));
  }
     }
 inputFile.close();
 }
 catch (IOException e) {
     System.out.println("Encountered IOException when reading the input file.");
 }

 if(DEBUG)
     System.out.println("Symbol table = " + st.toString());

 HuffmanTree ht = HuffmanTreeC.makeHuffmanCodingTree(st);

 if(DEBUG)
     System.out.println("Huffman coding tree = " + ht.toString());

 // We will now recursively walk the tree building up the bit strings as we go.
 // When we reach a leaf node, we will add the computed bit string to its symbol
 // table entry. This will facilitate writing the bit strings for the input
 // letters.
 //
 computeBitStrings(ht, st, 0, 0);

 if(DEBUG)
     System.out.println("Symbol table = " + st.toString());

 // We now have everything we need to write the compressed file. First reopen
 // the source file.
 //
 inputFile = io.openInputFile(this.args[0]);

 BinaryOut outputFile = io.openBinaryOutputFile();

 // 1. write the signature
 //
 outputFile.write(MAGIC_NUMBER, 16);

 // 2. write out the frequency table
 //
 if (DEBUG) 
     System.out.println("symbol table size = " + st.size());

 st.write(outputFile);

 // 3. read through the input text file again. This time, write the 
 // bit strings to the binary output file.
 //
 c = 0;
 try {
     while(c != -1) {
  c = inputFile.read();
  
  if(c != -1) {
      
      BitString bs = st.get(new Integer(c)).getBitString();
      
      int bits = bs.getBits();
      int size = bs.getSize();
      outputFile.write(bits, size);

      if(DEBUG) 
   System.out.println("wrote " + (char) c + " = " + bs.toString());
  }
     }
     inputFile.close();
     outputFile.flush();
     outputFile.close();
 }
 catch (IOException e) {
     System.out.println("hit with this IOException");
 }
    }

    public void computeBitStrings(HuffmanTree t, SymbolTable st, int bits, int count) {

 if(t.isLeaf()) {
     char ch = t.getChar();
     STValue value = (STValue) st.get(new Integer((int) ch));
     value.setBitString(new BitStringC(bits, count));
 }
 else {
     HuffmanTree 
  t1 = t.getLeft(),
  t2 = t.getRight();

     if(t1 != null) computeBitStrings(t1, st, bits * 2 | 0, count + 1);
     if(t2 != null) computeBitStrings(t2, st, bits * 2 | 1, count + 1);
 }
    }

    public static void main(String[] args) {

 new Huff(args).go();
    }
}
