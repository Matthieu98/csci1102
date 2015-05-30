import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class Util
{
	public static final int EMPTY = 0;
	public static final int PLAYER1 = 1;
	public static final int PLAYER2 = 2;

	// The dimensions of the board.
	//
	public static final int BOARDWIDTH = 7;
	public static final int BOARDSIZE = 7;

	public static final int RANDOMDISKS = 3;

	// Where to put the welcome greeting.
	//
	public static final int GREETING_X = 200;
   	public static final int GREETING_Y = 270;

   	// How large is the screen?
   	//
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 500;

	// Colors of balls.
	//
	public static final Color player1Color = Color.blue;
	public static final Color player2Color = Color.black;
	public static final Color emptyCellColor = Color.lightGray;

	// Timer stuff
	//
	public static final int ONE_SECOND = 1000;
}

