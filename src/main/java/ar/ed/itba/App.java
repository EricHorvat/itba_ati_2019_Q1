package ar.ed.itba;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    static JFrame mainFrame;
    
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        mainFrame = new JFrame("App");
        mainFrame.setContentPane(new ATIInterface().getMainPanel());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    public static void pack(){
        mainFrame.pack();
    }
}
