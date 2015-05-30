/***********************************************
 * File: SymbolComparator.java
 * Authors: Charlie Manclark & Jason Morse
 * Date: November 22, 2013
 * Class: Computer Science II - Problem Set 8
 ***********************************************/ 

import java.util.Comparator;

// Compares the frequencies of two characters to organize the elements of the frequency table
public class SymbolComparator implements Comparator<SymbolTableData> {
    
    public int compare(SymbolTableData a, SymbolTableData b) {
    
        if (a.getFrequency() >= b.getFrequency()) {
            return -1;
        }
        
        if (a.getFrequency() <= b.getFrequency()) {
            return 1;
        }
        
        else {
            return 0;
        }
    }
}