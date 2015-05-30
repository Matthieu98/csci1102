// file: Score.java
// author: Bob Muller
// date: April 20, 2012
//
class Score {

    private int player1;
    private int player2;

    public Score() {
        this.player1 = 0;
        this.player2 = 0;
    }

    public Score(int p1, int p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public int getPlayer1() { return this.player1; }
    public int getPlayer2() { return this.player2; }

    public void setPlayer1(int n) { this.player1 = n; }
    public void setPlayer2(int n) { this.player2 = n; }

    public void set(Score s) {
        setPlayer1(s.getPlayer1());
        setPlayer2(s.getPlayer2());
    }

    public void add(Score s) {
        setPlayer1(getPlayer1() + s.getPlayer1());
        setPlayer2(getPlayer2() + s.getPlayer2());
    }

    public void reset() {
        setPlayer1(0);
        setPlayer2(0);
    }
}
