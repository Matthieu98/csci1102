Computer Science 2
==================

This repository contains problem set assignments from CSCI1102 - Computer Science 2 (Fall 2013).

#####Problem Set 1 (Basic Intro to Java)
* `Runlength_starter.java` – Given an array of integers, this program will print out each unique element and how many times it appears consecutively.

#####Problem Set 2 (Classes/Objects)
* `Book.java` – A class that creates Book objects, each of which contains a title string and boolean (borrowed or available).
* `Library.java` – A class to create a Library object. Contains various methods to check the library's address, operating hours, borrow/return Book objects, and check on a Book's status.

#####Problem Set 3
* Non-coding assignment

#####Problem Set 4 (The Stack Data Structure)
* `Stack.java` – Interface for the resizing array stack class.
* `ResizingArrayStack.java` – A resizing array stack implementation taken from Algorithms, 4th Edition (Sedgewick & Wayne). Will be used in `Evaluate.java`. 
* `Evaluate.java` – This class uses an operator stack and an operands stack to evaluate basic arithmetic expressions. This is done by checking tokens for precedence and pushing/popping from the stacks accoridngly. (This is an extension of Dijkstra's Two-Stack Algorithm for Expression Evaluation.)

#####Problem Set 5 (The Queue & Deque Data Structures)
* `Deque.java` – Interface for the linked deque and resizing array deque classes.
* `LinkedDeque.java` – Implementation of a linked deque structure including: `pushLeft()`, `pushRight()`, `popLeft()`, `popRight()`, and `toString()`.
* `ResizingArrayStack.java` – Extension of `LinkedDeque.java`, allowing the data structure to resize--grow 2x if it runs out of space or shrink 2x if there's enough empty space.
* `StdIn.java` & `StdOut.java` - Helper I/O classes taken from Algorithms, 4th Edition (Sedgewick & Wayne).

#####Problem Set 6 (The Priority Queue)
* The following files in this problem set implement a priority queue data structure in different ways:
	*`OrderedArrayMaxPQ.java` – Implemented using array (for ordered data).
	*`OrderedLinkedListMaxPQ.java` – Implemented using linked list (for ordered data).
	*`UnorderedArrayMaxPQ.java` – Implemented using array (for unordered data).
	*`UnorderedLinkedListMaxPQ.java` – Implemented using linked list (for unordered data).
	*`linkedPQ.java` – Implemented using a heap-ordered binary tree.
* `PQ.java` – Interface for the linkedPQ class.
* `StdIn.java` & `StdOut.java` - Helper I/O classes taken from Algorithms, 4th Edition (Sedgewick & Wayne).

#####Problem Set 7 (The Binary Search Tree)
* `BST.java` – Interface for both BST classes
* `emptyImmutableBST.java` – Defines return values for each method in a situation where the BST is empty
* `immutableBST.java` – Implementation of a binary search tree data structure including: `get()`, `put()`, `deleteMin()`, `deleteMax()`, `delete()`, `contains()`, `floor()`, `height()`, and `toString()`.
* `StdIn.java` & `StdOut.java` - Helper I/O classes taken from Algorithms, 4th Edition (Sedgewick & Wayne).

#####Problem Set 8 (Huffman Encoding)
* `Huff.java` – Takes in a .txt file and converts the information into <key, frequency> pairs which are inserted into the symbol table. They are then ordered into a new frequency table which is converted into a PQ tree. This tree is manipulated to perform the compression and the result is returned as a .zip file. (This assignment is incomplete.)
* `HuffmanNode.java` – Implementation of the frequency table tree. 
* `SymbolComparator.java` – Contains a compare method to organize symbols in the symbol table based on frequency.
* `SymbolTableData.java` – Class that creates a symbol table (Treemap structure) to record symbols and their frequencies in order. 
* `FileIO.java` & `FileIOC.java` - Helper I/O classes provided by the professor

#####Problem Set 9 (Huffman Decoding)
* This is essentially the inverse of problem set 8. This will perform Huffman decoding on a .zip file and return the original .txt. The only new file is `Puff.java`. All other files are either helper classes for I/O, or solutions to the past problem set. 
* `Puff.java` - Creates a symbol based on the inputted file to store character and frequency information. Leaf node characters are written to the output text file. 

#####Problem Set 10 (AI in a Connect 4 Game)
* All files except `Player1.java` and `Player2.java` were provided by the professor (the game GUI).
* `Player1.java` & `Player2.java` - (Both are the same) This code represents an AI for a Connect4 game. It interprets the board as a 2-dimensional array and analyzes all potential moves at the start of each turn. It assigns a priority to each open spot and makes a defensive decision each time. (We originally attempted to make a minimaxing algorithm, but due to time constraints, we were forced to stick with the simpler defensive option.)


