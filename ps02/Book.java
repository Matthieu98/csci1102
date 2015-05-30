
/* File: Book.java
 * Name: Jason Morse
 * Date Modified: September 24, 2013
 * 
 * Authors:
 * Jones, Evan, Adam Marcus, and Eugene Wu. 
 * 6.092 Introduction to Programming in Java,
 * January IAP 2010. (Massachusetts Institute of Technology: MIT OpenCourseWare), http://ocw.mit.edu (Accessed 29 Jun, 2013). License: Creative Commons BY-NC-SA 
 * Julie Blumenthal for CS102 Fall 2013
 */
public class Book {

    String title;
    boolean borrowed;

    // Creates a new Book
    public Book(String bookTitle) {
        title = bookTitle;
    }
   
    // Marks the book as borrowed
    public void borrowed() {
    	 borrowed = true;
    }
   
    // Marks the book as not borrowed
    public void returned() {
    	borrowed = false;
    }
   
    // Returns true if the book is borrowed, false otherwise
    public boolean isBorrowed() {
    	return borrowed;
    }
   
    // Returns the title of the book
    public String getTitle() {
		return title;
    }

    public static void main(String[] arguments) {
        // Small test of the Book class
        Book example = new Book("Being There");
        System.out.println("Title (should be Being There): " + example.getTitle());
        System.out.println("Borrowed? (should be false): " + example.isBorrowed());
        example.borrowed();
        System.out.println("Borrowed? (should be true): " + example.isBorrowed());
        example.returned();
        System.out.println("Borrowed? (should be false): " + example.isBorrowed());
    }
} 