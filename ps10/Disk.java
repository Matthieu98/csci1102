// file: Disk.java
// author: Bob Muller
// date: November 20, 2013
//
// The Disk API. 
//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public interface Disk {

    // The constructor for Disks isn't specified in the interface
    // but we're documenting it here anyway.
    //
    //    public DiskC(int disk);

    public boolean isPlayer1();
    public boolean isPlayer2();
    public String toString();

    public JPanel getDisplayPanel();
}
