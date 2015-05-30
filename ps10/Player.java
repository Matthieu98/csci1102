import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
// import java.util.*;

public interface Player
{
    public int play(Board board, int opponentsPlay, Timer t);
    public String teamName();
}
