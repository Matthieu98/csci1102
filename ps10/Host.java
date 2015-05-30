// file: Host.java
// author: Bob Muller
// date: November 20, 2013
//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*;
// import java.util.*;

class Host implements ActionListener
{
    private boolean stepMode = true;
    private boolean stepRequested = false;
    
    private boolean diskWasDropped = false;
    private int diskDroppedInColumn;
    
    private boolean matchPlay = true;
    
    private Score matchScore;
    
    // This variable is set if the time runs out for a given
    // player.
    //
    private boolean timeRanOut = false;
    
    private int team1Wins = 0;
    private int team2Wins = 0;
    
    private String team1Name;
    private String team2Name;
    
    private Display mainDisplay;
    
    private ControlDisplay controlDisplay;
    
    private Connect4Frame frame;
    
    public Host()
    {
    }
    
    // Some setters.
    //
    private void setDiskWasDropped(boolean b) { diskWasDropped = b; }
    private void setDiskDroppedInColumn(int i) { diskDroppedInColumn = i; }
    private void setTimeRanOut(boolean b) { timeRanOut = b; }
    
    public void start()
    {
	Player player1 = new Player1();
	Player player2 = new Player2();

	// Assume we're not playing match play. If the user requests
	// match play, then each player will take a crack at the same
	// board.
	//
	team1Name = player1.teamName();
	team2Name = player2.teamName();

	frame = new Connect4Frame(Util.BOARDSIZE, new Listeners(), team1Name, team2Name);

	mainDisplay = frame.getDisplay();

	controlDisplay = mainDisplay.getControlDisplay();

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.setVisible(true);

	// Find out how many games are to be played.
	//
	String s = JOptionPane.showInputDialog("How many games?");
	int n = Integer.parseInt(s);

	if(Control.DEBUG) 
	    System.out.println("host.start: playing " + s + " games.");

	// Create a random number generator.
	//
	// Observe that we're not seeding the random number generator yet.
	// To seed it, we'll use the seeded constructor:
	//
	// java.util.Random(Calender.get(xxx)).
	//
	java.util.Random rand = new java.util.Random();

	// This starts off with score 0 and 0.
	//
	matchScore = new Score(0,0);

	// Play all of the games.
	//
	for(int i = 0; i < n; i++) {

	    // These are for the actual plays of the two players.
	    //
	    int play1 = 0,
		play2 = 0;

	    // The fourScore variable tracks the number of fours for
	    // each player.
	    //
	    Score fourScore = new Score(0,0);

	    // Gen up a fresh board.
	    //
	    Board board1 = new BoardC(mainDisplay,Util.BOARDSIZE);
	    Board board2 = new BoardC(mainDisplay,Util.BOARDSIZE);

	    // The following code will generate a random start of the
	    // game and then allow each player to have a crack at going
	    // first against the randomly generated board.  In particular,
	    // a random number of disks (goverened by Util.RANDOMDISKS
	    // which is currently set to 3) will be generated. Twice this
	    // many plus 1, disks will be inserted and then the defender
	    // will have the first move.  For example, if the random number
	    // is 3, then 3 disks for each player will be randomly inserted
	    // and then 1 more disk will be randomly inserted for the "goes
	    // first" player.  The "goes second" player will then get to
	    // choose their first move. Once this game is completed, the same
	    // random board will be constructed and the player roles will
	    // be reversed.
	    //
	    // NB: The current implementation will simply determine who wins
	    // the most games.  With the current "DeAngelo strategy", it might
	    // be more interesting to see who wins by the most four connects.
	    // We aren't doing that now.
	    //
	    int randomNumberOfDisks = rand.nextInt(Util.RANDOMDISKS) + 1;
	    
	    for(int j = 0; j < randomNumberOfDisks; j++) {
		
		play1 = rand.nextInt(Util.BOARDSIZE);
		board1.add(Util.PLAYER1, play1);
		board2.add(Util.PLAYER2, play1);
		
		play2 = rand.nextInt(Util.BOARDSIZE);
		board1.add(Util.PLAYER2, play2);
		board2.add(Util.PLAYER1, play2);
	    }

	    // Now add one more disk for player1 then let player2 choose
	    // their move.
	    //
	    play1 = rand.nextInt(Util.BOARDSIZE);
	    board1.add(Util.PLAYER1, play1);
	    board2.add(Util.PLAYER2, play1);

	    if (matchPlay) {

		// Now play the game with player1 having made 1 more move and
		// then play the same game with player2 having made 1 more move.
		//
		matchScore.add(playOneGame(board1, player1, player2, Util.PLAYER1, Util.PLAYER2, play1));
		controlDisplay.setMatchScores(matchScore);
		mainDisplay.displayBoard(board1);

		matchScore.add(playOneGame(board2, player2, player1, Util.PLAYER2, Util.PLAYER1, play1));
		controlDisplay.setMatchScores(matchScore);
		mainDisplay.displayBoard(board2);
	    }
	    else
		// Its not match play. Flip a coin to see whether to play board1
		// or board2.
		
		if(rand.nextInt(2) == 1) {
		    matchScore.add(playOneGame(board1, player1, player2, Util.PLAYER1, Util.PLAYER2, play1));
		    controlDisplay.setMatchScores(matchScore);
		    mainDisplay.displayBoard(board1);
		}
		else
		    {
			matchScore.add(playOneGame(board2, player2, player1, Util.PLAYER2, Util.PLAYER1, play1));
			controlDisplay.setMatchScores(matchScore);
			mainDisplay.displayBoard(board2);
		    }
	}
    }

    public Score playOneGame(Board board,
			     Player first,
			     Player second,
			     int playingFirst,
			     int playingSecond,
			     int playOfFirst)
    {
	// This method will play one game between the two players.  Note that the
	// game has been set up to make a small number of random moves for both players
	// and to have made one more move for the player playing first than for the
	// player playing second.  So this routine is called when the second player is
	// ready to respond to a move made by the player inserting the first disk.
	//
	// This variable will always hold the play of the player inserting the second
	// disk. NB: that this player inserts first in this procedure because this player
	// is behind one move.
	//
	int playOfSecond;
	
	Score fourScore = new Score(0,0);
	
	// This is the main loop. It is exited when the board is full or
	// when one of the players makes an illegal move.
	//
	while(!board.isFull()) {
	    
	    // Show them the board.
	    //
	    fourScore.set(board.countFoursInBoard());
	    controlDisplay.setFourScores(fourScore);

	    mainDisplay.displayBoard(board);

	    if(Control.DEBUG)
                System.out.println("playOneGame: stepMode = "+ stepMode);
	    
	    // If we're in stepping mode, wait until they click the step button or
	    // one of the drop buttons.
	    //
	    while (stepMode && !stepRequested && !diskWasDropped) debug(""); 

	    if(Control.DEBUG)
		debug("playOneGame: broke out of while polling loop...");

	    stepRequested = false;

	    if(diskWasDropped) {

		if(Control.DEBUG)
		    System.out.println("playOneGame: disk was dropped.");
	    		
		// We're in step mode and the human being clicked a drop button.
		// We'll slavishly drop a disk in the specified column for this
		// player and by-pass the play function of the Player class. NB
		// the diskWasDropped and diskDroppedInColumn variables are set
		// by the action listeners of the buttons.
		//
		diskWasDropped = false;
		playOfSecond = diskDroppedInColumn;
	    }
	    else {
		// OK. We're going to call on the player to make a move. To keep the
		// player from taking too long, we're going to put it on a timer. In
		// fact, we're going to have 2 timers.  The real one (i.e., mainTimer)
		// and a shorter one (i.e., the preTimer) that we'll pass to the player as
		// an alarm clock.  When the timers go off, an ActionEvent is signalled
		// and the timer's event handler's are run.
		//
		// NB: If the player wants to hear the alarm clock go off, it needs to add
		// an ActionListener to the timer that's been passed to it.
		//
		// NB: The timers are controlled by constants in Util and by setable
		// global variables. Yuck!
		//

	    if(Control.DEBUG)
                System.out.println("playOneGame: disk was NOT dropped.");

		Timer preTimer = new Timer(getPreTimerDelay(),
					   new ActionListener() {
					       public void actionPerformed(ActionEvent e) {}});

		preTimer.setRepeats(false);  // Just report timer elapse once.
		
		Timer mainTimer = new Timer(getMainTimerDelay(),this);
		mainTimer.setRepeats(false);
		
		// Copy the board.
		//
		Board boardCopy = new BoardC(mainDisplay,Util.BOARDSIZE,
					    board.getArray(), board.getIndexArray());

		// OK. Start the timers in parallel :-) and call the player.
		//
		preTimer.start();  mainTimer.start();
		
		playOfSecond = second.play(boardCopy,playOfFirst, preTimer);
		
		preTimer.stop(); mainTimer.stop();
		
		if (timeRanOut) {
		    System.out.println("Time ran out for player " + second.teamName());
		    setTimeRanOut(false);
		    return theLoserIs(playingSecond);
		}
	    }

	    // OK. They made a move in time.
	    //
	    if(board.isLegalPlay(playOfSecond)) {
		
	    if(Control.DEBUG)
                System.out.println("playOneGame: second player made a legal move.");


		// The play was legal add the disk.
		//
		board.add(playingSecond,playOfSecond);

		// If the previous play didn't fill the board, let the other guy
		// have a chance.
		//
		if(board.isFull())
		    
		    // The play just made, filled the board. Break out of the loop
		    // and see who won.
		    //
		    break;
	      
		else {
		    
		    // The play was legal and it didn't fill the board. If we're in stepping
		    // mode, compute and display the score.  NB that the counter is currently
		    // designed to count -both- players in one pass. So we're wasting work here.
		    // We'll elide this if performance is an issue.
		    //
		    if (stepMode) {
			fourScore.set(board.countFoursInBoard());
			controlDisplay.setFourScores(fourScore);
			mainDisplay.displayBoard(board);
		    }

		    // If we're in stepping mode, wait until the person clicks the step button or
		    // one of the drop buttons.
		    //
		    while (stepMode && !stepRequested && !diskWasDropped) 

			debug("waiting for a drop button ..."); 

		    stepRequested = false;
		    
		    // Either we've left stepping mode or a single step has been
		    // requested. See if the single step was request by a drop
		    // button.
		    //
		    if (diskWasDropped) {
			
			// We're in step mode and the human being clicked a drop button.
			// We'll slavishly drop a disk in the specified column for this
			// player and by-pass the play function of the Player class. NB
			// the diskWasDropped and diskDroppedInColumn variables are set
			// by the action listeners of the buttons.
			//
			diskWasDropped = false;
			playOfFirst = diskDroppedInColumn;
		    }
		    else
			{
			    // OK, we're going to call on the player playing first.
			    // Set up the timers.
			    //
			    Timer preTimer = new Timer(getPreTimerDelay(),
						       new ActionListener() {
							   public void actionPerformed(ActionEvent e){}});
			    preTimer.setRepeats(false);  // Just report timer elapse once.

			    Timer mainTimer = new Timer(getMainTimerDelay(), this);
			    mainTimer.setRepeats(false);

			    // Copy the board.
			    //
			    Board boardCopy = new BoardC(mainDisplay,Util.BOARDSIZE,
                                                         board.getArray(), board.getIndexArray());
			    
			    preTimer.start();  mainTimer.start();
			    
			    playOfFirst = first.play(boardCopy,playOfSecond, preTimer);
			    
			    preTimer.stop(); mainTimer.stop();
			    
			    if(timeRanOut) {
				
				System.out.println("Time ran out for " + first.teamName());
				setTimeRanOut(false);
				return theLoserIs(playingFirst);
			    }
			}
		    
		    // Check to see if this was a legal play.
		    //
		    if (board.isLegalPlay(playOfFirst))
			
			// The specified play was legal. Make the play for them.
			//
			board.add(playingFirst, playOfFirst);
		    
		    else
			// The first player made an illegal move. They lose!
			//
			return theLoserIs(playingFirst);
		}
	    }
	    else
		
		// The second player made an illegal move. They lose!
		//
		return theLoserIs(playingSecond);
	    
	} // End of main while loop.
	
	
	// OK. We've exited the main while loop because the board has been filled.
	// Compute and display the new score.
	//
	fourScore.set(board.countFoursInBoard());
	controlDisplay.setFourScores(fourScore);
	
	if (fourScore.getPlayer1() > fourScore.getPlayer2())
	    
	    // Player1 won.
	    //
	    return new Score(1,0);
	
	else
	    if (fourScore.getPlayer1() < fourScore.getPlayer2())
		
		// Player2 won.
		//
		return new Score(0,1);
	
	    else
		// It was a tie.
		//
		return new Score(0,0);
    }

    public void debug(String s) {
	System.out.println(s);
    }

    // Timer routines
    //
    public int getPreTimerDelay()
    {
	return 3 * Util.ONE_SECOND;
    }
    
    public int getMainTimerDelay()
    {
	return 4 * Util.ONE_SECOND;
    }
    
    // This is the event handler for the main timer.
    //
    public void actionPerformed(ActionEvent e)
    {
	setTimeRanOut(true);
    }
    
    public Score theLoserIs(int aPlayer)
    {
	if (aPlayer == Util.PLAYER1)
	    return new Score(0,1);
	else
	    return new Score(1,0);
    }
    
    class Listeners
    {
	private MatchPlayListener matchPlayListener;
	private GoListener goListener;
	private StepListener stepListener;
	private ButtonListener[] buttonListeners;
	private DelayListener delayListener;
	
	public Listeners()
	{
	    matchPlayListener = new MatchPlayListener();
	    
	    goListener = new GoListener();
	    stepListener = new StepListener();
	    buttonListeners = new ButtonListener[Util.BOARDWIDTH];
	    delayListener = new DelayListener();
	    
	    for(int i = 0; i < Util.BOARDWIDTH; i++)
		buttonListeners[i] = new ButtonListener(i);
	}
	
	public ActionListener getMatchPlayListener() { return matchPlayListener; }
	public ActionListener getGoListener() { return goListener; }
	public ActionListener getStepListener() { return stepListener; }
	public ActionListener getButtonListener(int i) { return buttonListeners[i]; }
	public ChangeListener getDelayListener() { return delayListener; }
    }
    
    class DelayListener implements ChangeListener {

	public DelayListener() {}

	public void stateChanged(ChangeEvent e) {

	    System.out.println("stateChange: here I am...");

	}
    }

    class ButtonListener implements ActionListener
    {
	private int id;
	
	public ButtonListener(int i)
	{
	    id = i;
	}
	
	public void actionPerformed(ActionEvent e)
	{
	    setDiskWasDropped(true);
	    setDiskDroppedInColumn(id);
	}
    }
    
    class MatchPlayListener implements ActionListener
    {
	public MatchPlayListener() {}
	public void actionPerformed(ActionEvent e) { matchPlay = true; }
    }
    
    class GoListener implements ActionListener
    {
	public GoListener() {}
	public void actionPerformed(ActionEvent e) { stepMode = false; }
    }
    
    class StepListener implements ActionListener
    {
	public StepListener() {}
	public void actionPerformed(ActionEvent e) { 
	    if(Control.DEBUG)
		System.out.println("step action performed.");
	    stepRequested = true; 
	}
    }
}