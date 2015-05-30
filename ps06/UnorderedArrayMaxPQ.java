/*************************************************************************
 * File: UnorderedArrayMaxPQ.java
 * Author: Jason Morse
 * Date: October 31, 2013
 * Class: Computer Science II - Problem Set 6
*******************************************************************************************************************/

public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] a; // Creates array
    private int N; // Integer to keep track of array size

    // Constructor
    public UnorderedArrayMaxPQ(int capacity) {
        a = (Key[]) new Comparable[capacity];
        N = 0;
    }

    // Determines if the array is empty
    public boolean isEmpty()   { return N == 0; }
    
    // Determines the size of the array
    public int size()          { return N; }
    
    // Inserts a value into the array
    public void insert(Key x)  { a[N++] = x; }
    
    // Deletes the maximum value from the array
    public Key delMax() {
        int max = 0;
        for (int i = 1; i < N; i++)
            if (less(max, i)) max = i;
        exch(max, N - 1);

        return a[--N];
    }
  
    // Compares two values
    private boolean less(int i, int j) {
        return (a[i].compareTo(a[j]) < 0);
    }

    // Exchanges the two values if necessary
    private void exch(int i, int j) {
        Key swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // Tester
    public static void main(String[] args) {
        UnorderedArrayMaxPQ<Integer> a = new UnorderedArrayMaxPQ<Integer>(10);
        a.insert(27);
        a.insert(6);
        a.insert(10);
        a.insert(2);
        while (!a.isEmpty()) 
            StdOut.println(a.delMax());
    }

}