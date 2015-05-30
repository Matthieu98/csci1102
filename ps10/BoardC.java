// file: BoardC.java
// author: Bob Muller
// date: November 20, 2013
//
// This is an implementation of the Board ADT.
//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
// import java.util.*;

class BoardC implements Board {
    // Instance variables.

    private Display mainDisplay;
    private int boardSize;

    private int player1Count;
    private int player2Count;

    // The index array has the index of the next row to
    // insert a disk in the board. I.e., index[i]
    // says where a disk will go in the ith column of
    // board.  NB that this array is intialized to
    // boardSize - 1 (i.e., the bottom).
    //
    private int[] index;
    private int[][] board;
    
    private int lastColumnPlayed = 0;
    private int lastRowPlayed = 0;

    // A constructor that doesn't receive values for the initial board.
    //
    public BoardC(Display d, int bs) {
        mainDisplay = d;
        boardSize = bs;

        this.board = new int[boardSize][boardSize];
        this.index = new int[boardSize];

        for(int i = 0; i < boardSize; i++) {

            this.index[i] = boardSize - 1;

            for(int j = 0; j < boardSize; j++)
                this.board[i][j] = Util.EMPTY;
        }
    }

    // A constructor that accepts an array of board values.
    //
    public BoardC(Display d, int bs, int[][]b, int[] idx) {
        mainDisplay = d;
        boardSize = bs;

        board = new int[boardSize][boardSize];
        index = new int[boardSize];

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++)
                board[i][j] = b[i][j];
            index[i] = idx[i];
        }
    }

    // Getters
    //
    public int[][] getArray() { return board; }
    public int getIndex(int i) { return index[i]; }
    public int[] getIndexArray() { return index; }
    public int getBoardSize() { return boardSize; }
    public int getPlayer1Count() { return player1Count; }
    public int getPlayer2Count() { return player2Count; }
    public int getLastColumnPlayed() { return lastColumnPlayed; }
    public int getLastRowPlayed() { return lastRowPlayed; }

    // Setters
    //
    public void setPlayer1Count(int n) { player1Count = n; }
    public void setPlayer2Count(int n) { player2Count = n; }
    
    public boolean columnIsFull(int column) { return index[column] < 0; }

    public boolean isLegalPlay(int column) {
        return column >= 0 && column < Util.BOARDWIDTH && !columnIsFull(column);
    }

    public boolean isFull() {
        for(int i = 0; i < boardSize; i++)
            if (!columnIsFull(i)) return false;
        return true;
    }

    public Score countFoursInBoard() {
        int p1count = 0,
            p2count = 0;

        for(int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                
                if( j <= boardSize - 4) {
                    
                    // Need to count this row.
                    //
                    int p1row = 0,
                        p2row = 0;

                    if (i <= boardSize - 4) {

                        // Need to count this column and the
                        // diagonals too.
                        //
                        int p1col = 0, p1lddiag = 0, p1rudiag = 0,
                            p2col = 0, p2lddiag = 0, p2rudiag = 0;
                        
                        for (int k = 0; k < 4; k++) {
   
                            if (board[i][j+k] == Util.PLAYER1) p1row++;
                            if (board[i][j+k] == Util.PLAYER2) p2row++;
                            
                            if (board[i+k][j] == Util.PLAYER1) p1col++;
                            if (board[i+k][j] == Util.PLAYER2) p2col++;

                            if (board[i+k][j+k] == Util.PLAYER1) p1lddiag++;
                            if (board[i+k][j+k] == Util.PLAYER2) p2lddiag++;

                            if (board[i+k][j+3-k] == Util.PLAYER1) p1rudiag++;
                            if (board[i+k][j+3-k] == Util.PLAYER2) p2rudiag++;
                        }   
                        p1count += countFours(p1row,p1col,p1lddiag,p1rudiag);
                        p2count += countFours(p2row,p2col,p2lddiag,p2rudiag);
                    }
                    else {
                        // No need to count this column or the
                        // diagonals. Just count the row.
                        //
                        for (int k = 0; k < 4; k++) {
                            if (board[i][j+k] == Util.PLAYER1) p1row++;
                            if (board[i][j+k] == Util.PLAYER2) p2row++;
                        }
                        p1count += countFours(p1row,0,0,0);
                        p2count += countFours(p2row,0,0,0);
                    }
                }
                else
                    if (i <= boardSize - 4) {
                        
                        // Need to count this column but not the row
                        // or the diagonals.
                        //
                        int p1col = 0,
                            p2col = 0;
                        
                        for (int k = 0; k < 4; k++) {
                            if (board[i+k][j] == Util.PLAYER1) p1col++;
                            if (board[i+k][j] == Util.PLAYER2) p2col++;
                        }
                        p1count += countFours(p1col,0,0,0);
                        p2count += countFours(p2col,0,0,0);
                    }
        // All done.
        //
        return new Score(p1count, p2count);
    }

    private int countFours(int n1, int n2, int n3, int n4) {
        int c = 0;
        if (n1 == 4) c++;
        if (n2 == 4) c++;
        if (n3 == 4) c++;
        if (n4 == 4) c++;
        
        return c;
    }

    public void add(int ball, int column) throws RuntimeException {
        if (columnIsFull(column))
            throw new RuntimeException();
        else {
            lastColumnPlayed = column;
            lastRowPlayed = index[column];

            board[index[column]--][column] = ball;
        }
    }

    public void display() { mainDisplay.displayBoard(this); }
}
