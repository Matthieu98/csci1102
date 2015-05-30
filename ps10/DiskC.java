// file: DiskC.java 
// author: Bob Muller
// date: November 20, 2013, revised from earlier Ball.java class.
//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class DiskC implements Disk {

    private int disk;

    public DiskC(int disk) {
	this.disk = disk;
    }

    public boolean isPlayer1() {
	return this.disk == Util.PLAYER1;
    }

    public boolean isPlayer2() {
	return this.disk == Util.PLAYER2;
    }

    public String toString() {
	if (isPlayer1())
	    return "DiskC[Player1]";
	else
	    return "DiskC[Player2]";
    }

    public JPanel getDisplayPanel() {
	Color c;
        
	if (isPlayer1())
	    c = Util.player1Color;
	else
	    if (isPlayer2())
		c = Util.player2Color;
	    else
		c = Util.emptyCellColor;
	
	return new FillPanel(c);
    }
}

