package ar.ed.itba;

import ar.ed.itba.ui.frames.MainFrame;
import ar.ed.itba.ui.frames.interfaces.MainInterface;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    
    public static void main( String[] args )
    {
        MainFrame mainFrame = MainFrame.instance();
        mainFrame.build();
        mainFrame.makeShow();
    }
    
}
