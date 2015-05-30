/********************************************
  * File: Puff.java
  * Authors: Charlie Manclark & Jason Morse
  * Date: December 4, 2013
  * Class: Computer Science II - PS9
*********************************************/ 

import java.io.*;
import java.util.*;

public class Puff {
    
    // Instance variables
    public static final int MAGIC_NUMBER = 0x00BC;
    
    private final String[] args;
    
    // Construtor
    public Puff(String[] args) {
        this.args = args;
    }
    
    private void go() {
        
        // Reads the first two bytes of the input file and checks for .zip magic number
        FileIO io = new FileIOC();
        BinaryIn inputFile = io.openBinaryInputFile(this.args[0]);
        int x = inputFile.readInt(16);
        if (x != MAGIC_NUMBER) {
            System.out.println("Error: Not a zip file.");
            return;
        }
        
            // Builds a symbol table to store character and frequency information
            SymbolTable<Integer, STValue> st = new SymbolTableC<Integer, STValue>(inputFile);
            
            // Builds a Huffman coding tree based on the symbol table
            HuffmanTree ht = HuffmanTreeC.makeHuffmanCodingTree(st);
            
            // Opens the output text file
            FileWriter outputFile = io.openOutputFile();
            
            // Writes the appropriate leaf node characters into the output text file
            while (!ht.isLeaf()) {
                int b = inputFile.readInt(1);
                if (b == 0) {
                    ht = ht.getLeft(); 
                } else {
                    ht = ht.getRight();
                }
            }
                
            char ch = ht.getChar();
            System.out.println(ch);
            
            try {
                outputFile.write(ch); }
            catch(IOException e) {
                System.out.println("hit with this IOException"); }

            // Closes the output text file
            try {
                outputFile.close(); }
            catch(IOException e) {
                System.out.println("hit with this IOException"); }
        }
    
    // Main method
    public static void main(String[] args) {
        new Puff(args).go();
    }
}

      