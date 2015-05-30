/***********************************************************
 * File: ResizingArrayDeque.java
 * Name: Jason Morse
 * Date: October 21, 2013
 * Class: CS102 (T/TH 3 PM), Professor Muller, Problem Set 5
************************************************************/
import java.util.NoSuchElementException;

public class ResizingArrayDeque<Item> implements Deque<Item> {
  
    // Instance variables.
    private Item[] deque;
    private int N = 0;
    private int left = 0;
    private int right = 0;
    
    // Creates empty deque.
    public ResizingArrayDeque() {
        deque = (Item[]) new Object[2];
    }
        
    // Returns the number of elements in the deque.
    public int size() {
        return N;
    }
    
    // Determines if the deque is empty.
    public boolean isEmpty() {
        return size() == 0;
    }
        
    // Converts the item to a string that can be printed.
    public String toString() {
        int i = left;
        String string = "";
        while (i != (right - 1)) {
            string = string + deque[i] + ", ";
            i++;
        }
        string = string + deque[i];
        return string;
    }

    // Resizes the array.
    private void resize(int max) {
        assert max >= N;
        Item [] resizeArray = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            resizeArray[i] = deque[(left + i) % deque.length];
        }
        deque = resizeArray;
    }
    
    // Pushes elements onto the left side of the array.
    public void pushLeft(Item item) {
        if (N == deque.length) resize(2 * deque.length);
        if (isEmpty()) {
            deque[left] = item;
        }
        else {
            for (int i = 0; i < N; i++) {
                deque[N - i] = deque[N - i - 1];
                deque[left] = item;
                right++;
                }
                right++;
                
        }
        if (left == deque.length) left = 0;
        N++;
    }
    
    // Pushes elements onto the left side of the array.
    public void pushRight(Item item) {
        if (N == deque.length) resize(2 * deque.length);
        if (isEmpty()) {
            deque[left] = item;
            right++;
            N++;
        }
        else {
          for (int i = 0; i < N; i++) {
            deque[N] = deque[N + i];
            }
            int oldRight = right;
            deque[oldRight] = item;
            right++;
            N++;
            if (right == deque.length) resize(2 * deque.length);
        }
        if (right == deque.length) right = 0;
        N++;
    }
    
    // Pops an element off of the left side of the array.
    public Item popLeft() {
        if (isEmpty()) throw new NoSuchElementException("The array is empty.");
        Item item = deque[left];
        deque[left] = null;
        left++;
        N--;
        if (left == deque.length) left = 0;
        if (N < 0 && N == deque.length / 4) resize(deque.length / 2);
        return item;
    }
    
    // Pops an element off of the right side of the array.
    public Item popRight() {
        if (isEmpty()) throw new NoSuchElementException("The array is empty.");
        Item item = deque[right - 1];
        deque[right - 1] = null;
        right--;
        N--;
        if (right == deque.length) right = 0;
        if (N > 0 && N == deque.length / 4) resize(deque.length / 2);
        return item;
    }
    
    // Main method tests the funcitonality of push and pop methods.
    public static void main(String[] args) {
        Deque<String> deque = new ResizingArrayDeque<String>();
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
          