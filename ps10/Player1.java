/*******************************************
  * File: Player1.java
  * Authors: Jason Morse & Charlie Manclark
  * Date: December 11, 2013
  * Class: Computer Science II - PS10
*******************************************/ 

class Player1 implements Player {

    public int play(Board board, int opponentsPlay, Timer ignored) {
        
        // Assigns a variable to the 2D array that represents the board
        int[][] newBoard = board.getArray();
        
        // Assigns a variable to the array that represents the indexes of the columns
        int[] index = board.getIndexArray();
        
        // Creates an array to hold information regarding column priority (high number = greater importance)
        int[] priority = new int[7];
        
        // Initializes each value in the priority array to 0
        for (int i = 0; i < 7; i++) priority[i] = 0;
        
        for (int i = 0; i < 7; i++) {
            
            // If the column is full, assign a priority of 0
            if (index[i] == 7){ 
                int row = 0; 
                priority[i] = 0;
            }
            
            // Corrects 'out of bounds' error
            else if (index[i] == -1) {
                int row = 6;
            }
            
            // Analyses the board and assigns a priority to each column
            else {
                int col = i;
                int row = index[i];
                System.out.println(row);
                System.out.println(index[i]);
            
                // Analyzes horizontal
                if ((col >= 3) && (newBoard[row][col-1] == 2) && (newBoard[row][col-2] == 2) && (newBoard[row][col-3] == 2))
                    priority[i] = priority[i] + 8;
                
                
                if ((col <= 3) && (newBoard[row][col+1] == 2) && (newBoard[row][col+2] == 2) && (newBoard[row][col+3] == 2))
                    priority[i] = priority[i] + 8;
                
                // Analyzes vertical
                if ((row <= 2) && (newBoard[row+1][col] == 2) && (newBoard[row+2][col] == 2) && (newBoard[row+3][col] == 2))
                    priority[i] = priority[i] + 8;
                
                // Analyzes left diagonal
                if ((col >= 3) && (row <= 2) && (newBoard[row+1][col-1] == 2) && (newBoard[row+2][col-2] == 2) && (newBoard[row+3][col-3] == 2))
                    priority[i] = priority[i] + 8;
                
                if ((col <= 3) && (row <= 2) && (newBoard[row+1][col+1] == 2) && (newBoard[row+2][col+2] == 2) && (newBoard[row+3][col+3] == 2))
                    priority[i] = priority[i] + 8;
                
                if ((col >= 3) && (row >= 3) && (newBoard[row-1][col-1] == 2) && (newBoard[row-2][col-2] == 2) && (newBoard[row-3][col-3] == 2))
                    priority[i] = priority[i] + 8;
                
                if ((col <= 3) && (row >= 3) && (newBoard[row-1][col+1] == 2) && (newBoard[row-2][col+2] == 2) && (newBoard[row-3][col+3] == 2))
                    priority[i] = priority[i] + 8;
                
                if ((col >= 2) && (newBoard[row][col-1] == 2) && (newBoard[row][col-2] == 2))
                    priority[i] = priority[i] + 4;
                
                // Analyzes right diagonal
                if ((col <= 4) && (newBoard[row][col+1] == 2) && (newBoard[row][col+2] == 2)) 
                    priority[i] = priority[i] + 4;
                
                // Analyzes vertical
                if ((row <= 3) && (newBoard[row+1][col] == 2) && (newBoard[row+2][col] == 2))
                    priority[i] = priority[i] + 4;
                
                // Analyzes left diagonal
                if ((col >= 2) && (row <= 3) && (newBoard[row+1][col-1] == 2) && (newBoard[row+2][col-2] == 2))
                    priority[i] = priority[i] + 4;
                
                if ((col <= 4) && (row <= 3) && (newBoard[row+1][col+1] == 2) && (newBoard[row+2][col+2] == 2))
                    priority[i] = priority[i] + 4;
                
                if ((col >= 2) && (row >= 2) && (newBoard[row-1][col-1] == 2) && (newBoard[row-2][col-2] == 2))
                    priority[i] = priority[i] + 4;
                
                if ((col <= 4) && (row >= 2) && (newBoard[row-1][col+1] == 2) && (newBoard[row-2][col+2] == 2))
                    priority[i] = priority[i] + 4;
                
                if ((col >= 1) && (newBoard[row][col-1] == 2))
                    priority[i] = priority[i] + 2;
                
                // Analyzes right diagonal
                if ((col <= 5) && (newBoard[row][col+1] == 2))
                    priority[i] = priority[i] + 2;
                
                // Analyzes vertical 
                if ((row <= 4) && (newBoard[row+1][col] == 2))
                    priority[i] = priority[i] + 2;
                
                // Analyzes left diagonal
                if ((col >= 1) && (row <= 4) && (newBoard[row+1][col-1] == 2))
                    priority[i] = priority[i] + 2;
                
                if ((col <= 5) && (row <= 4) && (newBoard[row+1][col+1] == 2))
                    priority[i] = priority[i] + 2;
                
                if ((col >= 1) && (row >= 1) && (newBoard[row-1][col-1] == 2))
                    priority[i] = priority[i] + 2;
                
                if ((col <= 5) && (row >= 1) && (newBoard[row-1][col+1] == 2))
                    priority[i] = priority[i] + 2;
            }
         }
         
         // Instance variables
         int max = 0;
         int column = 3;
         int track = 0;
         
         // Determines which column has highest priority
         for (int i = 0; i < 7; i++) {
             if (priority[i] > max) {max = priority[i]; column = i;}
             track = track + priority[i];
         }
         
         // If there is no priority on the board, pick a random column
         if (track == 0) column = (int) (Math.random() * 7);
         return column;
    }

    public String teamName() { return "Binary Bros"; }
}