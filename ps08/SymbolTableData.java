/***********************************************
 * File: SymbolTableData.java
 * Authors: Charlie Manclark & Jason Morse
 * Date: November 22, 2013
 * Class: Computer Science II - Problem Set 8
 ***********************************************/ 

public class SymbolTableData {
    
    // Instance variables
    int frequency;
    int code;
 
    // Determines frequency of a character
    public SymbolTableData(int frequency){
        this.frequency = frequency;
    }
 
    // Determines frequency of a character
    public int getFrequency() {
        return frequency;
    }
    
    // Sets frequency of a character
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    // Gets the binary code of a character
    public int getCode() {
        return code;
    }
    
    // Sets the binary code of a character
    public void setCode(int code) {
        this.code = code;
    }
    
    // Converts the frequency and code to a string
    public String toString(){
        return "frequency: " + frequency + " code: " + code;
    }
 
}
