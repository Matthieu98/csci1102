/*******************************************************************************************************************
 * Problem 2.4.24: Priority queue with explicit links. 
 * 
 * Implement a priority queue using a heap-ordered binary tree, but use a triply linked structure instead 
 * of an array. You will need three links per node: two to traverse down the tree and one to traverse up the tree. 
 * Your implementation should guarantee logarithmic running time per operation, even if no maximum priority-queue 
 * size is known ahead of time.
 * 
 * File: linkedPQ.java
 * Author: Jason Morse
 * Date: October 31, 2013
 * Class: Computer Science II - Problem Set 6
 * 
*******************************************************************************************************************/

public class linkedPQ implements PQ<Item> {
    
    private class PQNode {
      
        Object data;
        PQNode parent;
        PQNode leftChild;
        PQNode rightChild;
        
        public PQNode(Object value) {
            this.data = value;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }
    }
    
    PQNode root;
    
    public linkedPQ() {
        this.root = null;
    }
   
    public boolean isEmpty() {
        return this.root == null;
    }
   
    public void insert(Object item) {
        PQNode prev = null;
        PQNode current = this.head;
        while (current != null && current.data.value >= item.value) {
           prev = current;
           current = current.next;
        }
        
        PQNode temp = new PQNode(item);
        
        if (prev == null) {
           temp.next = this.head;
           this.head = temp;
        } else {
            temp.next = current;
            prev.next = temp;
        }   
     }

     public Object delete() {
        Object temp = this.head.data;
        this.head = this.head.next;
        return temp;
     }
}

