// file: Board.java
// author: Bob Muller
// date: November 20, 2013
//
public interface Board {

    //    public BoardC(Display d, int bs);
    //    public BoardC(Display d, int bs, int[][]b, int[] idx);

    public int[][] getArray();
    public int getIndex(int i);
    public int[] getIndexArray();
    public int getBoardSize();
    public int getPlayer1Count();
    public int getPlayer2Count();
    public int getLastColumnPlayed();
    public int getLastRowPlayed();
    public void setPlayer1Count(int n);
    public void setPlayer2Count(int n);
    public boolean columnIsFull(int column);
    public boolean isLegalPlay(int column);
    public boolean isFull();
    public Score countFoursInBoard();
    public void add(int ball, int column);
    public void display();
}
