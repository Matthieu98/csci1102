/* File: Library.java
 * Name: Jason Morse
 * Date Modified: September 24, 2013
 * Julie Blumenthal for CS102 Fall 2013
 */

import java.util.ArrayList;

public class Library {

	// Sets instance variables
	String location;
	Book title;
	ArrayList<Book> list = new ArrayList<Book>();

	// Assigns string 'address' to location
	public Library(String address) { 
		location = address;
	}
	
	// Prints hours for the libraries
	public static void printOpeningHours() {
		System.out.println("Libraries are open daily from 9 a.m. - 5 p.m.");
	}
	
	// Gets string 'address'
	public String getAddress() {
		return location;
	}
	
	// Prints location that correctly corresponds to Library 1 or Library 2
	public void printAddress() {
		System.out.println(location);
	}
	
	// Adds each book to ArrayList list
	public void addBook(Book title) {
		list.add(title);
	}
	
	// Prints all books that exist within ArrayList list
	public void printAvailableBooks() {
		if (list.size() == 0)
			System.out.println("No books in catalog.");
		for (Book b : list)
				System.out.println(b.getTitle());
	}
	
	// Marks boolean borrowed as true if book is in ArrayList list
	// If checked out or not in library, prints error message
	public void borrowBook(String title) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getTitle().equals(title)) {
				if (list.get(i).isBorrowed()) {
					System.out.println("Sorry, " + title + " is already checked out.");
					return;
				}
				list.get(i).borrowed();
				System.out.println("Successfully borrowed " + title + ".");
				return;
			} 
		System.out.println("Sorry, " + title + " is not in our catalog.");
	}
	
	// Marks boolean borrowed as false if book is in ArrayList list; if not in library, prints error message
	public void returnBook(String title) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getTitle().equals(title)) {
				if (list.get(i).isBorrowed()) {
					list.get(i).returned();
					System.out.println("You've successfully returned " + title + ".");
					return;
				}
			
			}
		System.out.println("Sorry, " + title + " is not in our catalog. Please check other local libraries to return to proper location.");
	}
	
	public static void main(String[] args) {
        // Create two libraries
        Library firstLibrary = new Library("10 Main St.");
        Library secondLibrary = new Library("228 Liberty St.");
        
        // Add five books to the first library
        firstLibrary.addBook(new Book("Being There"));
        firstLibrary.addBook(new Book("Bonnard"));
        firstLibrary.addBook(new Book("Franny and Zooey"));
        firstLibrary.addBook(new Book("Nicholas and Alexandra"));
        firstLibrary.addBook(new Book("The Palace Thief"));
        
        // Print opening hours and the addresses
        System.out.println("Library hours:");
        printOpeningHours();
        System.out.println();

        System.out.println("Library addresses:" + '\n');
        firstLibrary.printAddress();
        secondLibrary.printAddress();
        System.out.println();

        // Try to borrow Franny and Zooey from both libraries
        System.out.println("Borrowing Franny and Zooey from Library1:");
        firstLibrary.borrowBook("Franny and Zooey");
        System.out.println("Borrowing Franny and Zooey from Library1:");
        firstLibrary.borrowBook("Franny and Zooey");
        System.out.println("Borrowing Franny and Zooey from Library2:");
        secondLibrary.borrowBook("Franny and Zooey");
        System.out.println();

        // Print the titles of all available books from both libraries
        System.out.println("Books in catalog of the first library:");
        firstLibrary.printAvailableBooks();
        System.out.println();
        System.out.println("Books in catalog of the second library:");
        secondLibrary.printAvailableBooks();
        System.out.println();

        // Return Franny and Zooey to the first library
        System.out.println("Returning Franny and Zooey:");
        firstLibrary.returnBook("Franny and Zooey");
        System.out.println();

        // Try to return Franny and Zooey to the second library
        System.out.println("Returning Franny and Zooey:");
        secondLibrary.returnBook("Franny and Zooey");
        System.out.println();
        
        // Print the titles of available from the first library
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();

    }	

} 