/***********************************************
 * File: HuffmanNode.java
 * Authors: Charlie Manclark & Jason Morse
 * Date: November 22, 2013
 * Class: Computer Science II - Problem Set 8
 ***********************************************/ 

public class HuffmanNode implements Comparable<HuffmanNode> {
    
    // Instance variables
    private char value;
    private int position;
    private int frequency;
    public HuffmanNode left, right, ancestor;
    
    // Constructor
    public HuffmanNode(char value,int frequency) {
        this.value = value;
        this.frequency = frequency;
        left = right = null;
    }
 
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.left = left;
        this.right = right;
        this.frequency = frequency;
        this.ancestor = ancestor;
    }
    
    // Sets parent in huffman tree
    public void setAncestor(HuffmanNode ancestor) {
        this.ancestor = ancestor;
    }
    
    // Determines if a node in the huffman tree is a leaf
    public boolean isLeaf() {
        if (right == null && left == null)
            return true;
        return false;
    }
    
    // Adds a node to the huffman tree
    public void add(HuffmanNode node) {
        if (left == null)
            left = node;
        else if (right == null)
            right = node;
    }
    
    // Determines the frequency of a character
    public int getFrequency() {
        return frequency;
    }

    // Sets the frequency of a character
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    // Determines the left child of a node in the huffman tree
    public HuffmanNode getLeft() {
        return left;
    }

    // Determines the right child of a node in the huffman tree
    public HuffmanNode getRight() {
        return right;
    }

    // Determines the value of a character
    public char getValue() {
        return value;
    }
    
    // Sets the value of a character
    public void setValue(char value) {
        this.value = value;
    }
    
    // Determines the position of a node
    public int getPosition() {
        return position;
    }
    
    // Sets the position of a node
    public void setPosition(int position) {
        this.position = position;
    }
    
    // Converts frequency to a string
    public String toString(){
        return ": " + frequency + " Left: " + left.toString() + " Right: " + right.toString();
    }
 
    // Overrides the compareTo function
    @Override
    public int compareTo(HuffmanNode input) {
        if(input.getFrequency() > this.getFrequency())
            return -1;
        if(input.getFrequency() < this.getFrequency())
            return 1;
        return -1;
    }

}
