/******************************************************************************************************************
 * File: OrderedArrayMaxPQ.java
 * Author: Jason Morse
 * Date: October 31, 2013
 * Class: Computer Science II - Problem Set 6
*******************************************************************************************************************/

public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] a; // Creates array
    private int N; // Integer to keep track of array size

    // Constructor
    public OrderedArrayMaxPQ(int capacity) {
        a = (Key[]) (new Comparable[capacity]);
        N = 0;
    }

    // Determines if the array is empty
    public boolean isEmpty() { return N == 0; }
    
    // Determines the size of the array
    public int size()        { return N; } 
    
    // Deletes the maximum value from the array
    public Key delMax()      { return a[--N]; }

    // Inserts a value into the array
    public void insert(Key key) {
        int i = N - 1;
        while (i >= 0 && less(key, a[i])) {
            a[i + 1] = a[i];
            i--;
        }
        a[i + 1] = key;
        N++;
    }

    // Compares two values
    private boolean less(Key v, Key w) {
        return (v.compareTo(w) < 0);
    }
    
    // Exchanges the two values if necessary
    private void exch(int i, int j) {
        Key swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // Tester
    public static void main(String[] args) {
        OrderedArrayMaxPQ<Integer> a = new OrderedArrayMaxPQ<Integer>(10);
        a.insert(27);
        a.insert(6);
        a.insert(10);
        a.insert(2);
        while (!a.isEmpty())
            StdOut.println(a.delMax());
    }

}