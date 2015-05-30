/***********************************************************
 * File: LinkedDeque.java
 * Name: Jason Morse
 * Date: October 21, 2013
 * Class: CS102 (T/TH 3 PM), Professor Muller, Problem Set 5
***********************************************************/

import java.util.NoSuchElementException;

public class LinkedDeque<Item> implements Deque<Item> {
    
    // Instance variables
    private int N;
    private Node<Item> left;
    private Node<Item> right;
    
    // Establishes the doubly-linked list by setting an item, next, and previous identifiers 
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }
    
    // Sets initial values
    public LinkedDeque() {
        left = null;
        right = null;
        N = 0;
    }
    
    // Returns the number of elements in the deque.
    public int size() { return N; }
    
    // Determines if the deque is empty.
    public boolean isEmpty() { return size() == 0; }
    
    // Converts the item to a string that can be printed.
    public String toString() {
      Node<Item> i = left;
        String string = "";
        while (i.next != null) {
            string = string + i.item + ", ";
            i = i.next;
        }
        string = string + i.item;
        return string;
    }
    
    // Pushes elements onto the left side of the queue.
    public void pushLeft(Item item) {
        if (null == item) { throw new NullPointerException("Cannot add a null value"); }
        Node<Item> oldLeft = left;
        left = new Node<Item>();
        left.item = item;
        if (oldLeft != null) { oldLeft.prev = left; }
        if (isEmpty()) right = left;
        else           left.next = oldLeft;
        N++;

    }
    
    // Pushes elements onto the right side of the queue.
    public void pushRight(Item item) {
        Node<Item> oldRight = right;
        right = new Node<Item>();
        right.item = item;
        right.next = null;
        if (oldRight != null) { oldRight.next = right; }
        if (isEmpty()) left = right;
        else           right.prev = oldRight;
        N++;

    }
  
    // Pops an element off of the left side of the queue.
    public Item popLeft() {
        if (isEmpty()) throw new NoSuchElementException("The queue is empty.");
        Item item = left.item;
        left = left.next;
        left.prev = null;
        N--;
        if (isEmpty()) right = null;
        return item;
    }
    
    // Pops an element off of the right side of the queue.
    public Item popRight() {
        if (isEmpty()) throw new NoSuchElementException("The queue is empty.");
        Item item = right.item;
        right = right.prev;
        right.next = null;
        N--;
        if (isEmpty()) left = null;
        return item;
    }
    
    // Main method tests the funcitonality of push and pop methods.
    public static void main(String[] args) {
        Deque<String> deque = new LinkedDeque<String>();
        StdOut.println("TESTING RESIZING ARRAY DEQUE IMPLEMENTATION... \n");
        StdOut.println("Pushing A and B to the left side of the deque...");
        deque.pushLeft("B");
        deque.pushLeft("A");
        StdOut.println("Updated Deque: (" + deque.toString() + "). \n");
        StdOut.println("Pushing C and D to the right side of the deque...");
        deque.pushRight("C");
        deque.pushRight("D");
        StdOut.println("Updated Deque: (" + deque.toString() + "). \n");
        StdOut.println("Popping one element from the left should return A...");
        StdOut.println(deque.popLeft());
        StdOut.println("Updated Deque: (" + deque.toString() + "). \n");
        StdOut.println("Popping one element from the right should return D...");
        StdOut.println(deque.popRight());
        StdOut.println("Updated Deque: (" + deque.toString() + "). \n");
    }

}