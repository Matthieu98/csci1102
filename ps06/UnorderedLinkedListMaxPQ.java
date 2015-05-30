/******************************************************************************************************************
 * File: UnorderedLinkedListMaxPQ.java
 * Author: Jason Morse
 * Date: October 31, 2013
 * Class: Computer Science II - Problem Set 6
*******************************************************************************************************************/

import java.util.NoSuchElementException;

public class UnorderedLinkedListMaxPQ<Item extends Comparable<Item>> {
    private Node first; // Creates first node
    private int N; // Integer to keep track of list size

    // Defines a node
    private class Node {
        private Item value;
        private Node next;
    }

    // Constructor
    public UnorderedLinkedListMaxPQ() {
        first = null;
        N = 0;
    }

    // Determines if the list is empty
    public boolean isEmpty() { return N == 0; }

    // Determines the size of the list
    public int size() { return N; }
    
    // Inserts a value into the array
    public void insert(Item value) {
        Node oldfirst = first;
        first = new Node();
        first.value = value;
        first.next = oldfirst;
        N++;
    }
/** // COULDN'T COMPLETE THIS METHOD
    // Deletes the maximum value from the list
    public Item removeMax() {
        if (isEmpty()) { throw new NoSuchElementException("There are no values in the priority queue."); }
        else if (N == 1) {
           Item value = first.value;
           first = first.next;
           N--;
           return value;
        }
        else if (N != 0) {
          // ?
        }
    }
 **/
    
    // Compares two values
    private boolean less(Item v, Item w) {
        return (v.compareTo(w) < 0);
    }

    // Tester
    public static void main(String[] args) {
        UnorderedLinkedListMaxPQ<Integer> pq = new UnorderedLinkedListMaxPQ<Integer>();
        pq.insert(27);
        pq.insert(6);
        pq.insert(10);
        pq.insert(2);
//        while (!pq.isEmpty())
//            StdOut.println(pq.removeMax());
   }
   
}