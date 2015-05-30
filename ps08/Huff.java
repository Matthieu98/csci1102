/***********************************************
 * File: Huff.java
 * Authors: Charlie Manclark & Jason Morse
 * Date: November 22, 2013
 * Class: Computer Science II - Problem Set 8
 ***********************************************/ 

/**
 * So basically, we have put all of the (key, frequency) pairs into the TreeMap "symbolTable" and then ordered them into a new treeMap
 * "frequencyTable". We feed "frequencyTable" into a method that constructs a PQ tree based on frequency. Then, we remove two elements
 * from the tree at a time, add them together, and put the result back into the tree until there is only one node left. We are trying to
 * feed this resulting huffman tree into the method that traverses the tree recursively in order to generate a binary bitcode for each
 * leafNode. However, we can't seem to get this method to work properly. Therefore, we also can't output any data to a .zip file. 
 * 
 * Please Note: the .txt input file location must be changed in the main method.
 * 
 **/ 

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Huff {
    
    // Instance variables
    private Map<Character,SymbolTableData> symbolTable =  new TreeMap<Character, SymbolTableData>();
    SymbolComparator comp = new SymbolComparator();
    private Map<SymbolTableData, Character> frequencyTable =  new TreeMap<SymbolTableData, Character>(comp);
    private Map<SymbolTableData, Character> binaryTable =  new TreeMap<SymbolTableData, Character>();
    private PriorityQueue<HuffmanNode> treeQueue = new PriorityQueue<HuffmanNode>();
    public static final boolean DEBUG=false;
    public String path;
    public String p = "";
    HuffmanNode tree;
    
    // Takes in .txt file as a string
    public Huff(String path) {
        this.path = path;
    }
    
    // Reads in the .txt file
    public void readInFile() {
        // Maps characters to their corresponding frequencies
        In input = new In(new File(path));
        while (input.hasNextLine()) {
            char current = input.readChar();
            if (symbolTable.containsKey(current)) {
                SymbolTableData tempData = symbolTable.get(current);
                tempData.setFrequency(tempData.getFrequency() + 1);
            } else {
                symbolTable.put(current, new SymbolTableData(1));
            }
        }   
        
        // Puts the symbol table elements into an ordered frequency table
        for (Entry<Character, SymbolTableData> a : symbolTable.entrySet())
            frequencyTable.put(a.getValue(), a.getKey());
        
        for (Entry<SymbolTableData, Character> a : frequencyTable.entrySet())
            System.out.println(a.getValue().toString() + ": " + a.getKey().toString());
     }
    
    // Constructs a huffman tree (priority queue) based on the ordered frequency table
    public void constructTree() {
        for (Entry<SymbolTableData, Character> a  :  frequencyTable.entrySet()) {
            HuffmanNode leafNode = new HuffmanNode(a.getValue(), a.getKey().getFrequency());
            treeQueue.add(leafNode);
            
        }
        
        // Removes two nodes from the huffman tree (priority queue), adds their frequencies together, and puts the resulting node back in
        while (treeQueue.size() > 1) {
            HuffmanNode left = treeQueue.poll();
            HuffmanNode right = treeQueue.poll();
            HuffmanNode tree = new HuffmanNode(left.getFrequency() + right.getFrequency(), left, right);
            left.setAncestor(tree);
            right.setAncestor(tree);
            treeQueue.add(tree);
        }
        
        // Confirms that all nodes have been added 
        if (treeQueue.size() == 1) {
            System.out.println("Done"); 
        }

        // Prepares the huffman tree to be 'walked' to determine binary code
        tree = treeQueue.poll(); // recursively walk through it and create bit path
        pathConstruct(tree, p);  
    }
    
    // Walks down the huffman tree and creates a binary code for each character 
    public void pathConstruct(HuffmanNode current, String p) {
        if (current == null) return;
        if ((tree.left == null) && (tree.right == null)) {
            int i = Integer.valueOf(p);
            binaryTable.put(new SymbolTableData(i), current.getValue());
             for (Entry<SymbolTableData, Character> a : binaryTable.entrySet())
            System.out.println(a.getValue().toString() + ": " + a.getKey().toString());
        }
        else {
            pathConstruct(current.left, p + "0");
            pathConstruct(current.right, p + "1");
            return;
        }
    }

    // Main tester method
    public static void main(String[] args){
        Huff test = new Huff("/Users/Jason/Desktop/Ezekiel.txt");
        test.readInFile();
        test.constructTree();
    }
}
 
