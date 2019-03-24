package ar.ed.itba.ui.frames;

import javax.swing.*;
import java.awt.*;

public abstract class ATIFrame extends JFrame {
	
	protected ATIFrame(String title) throws HeadlessException {
		super(title);
	}
	
	public void build(){
		setContentPane(getMainPanel());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void makeShow(){
		pack();
		setVisible(true);
	}
	
	public void buildAndShow(){
		build();
		makeShow();
	}
	
	protected abstract JPanel getMainPanel();
}
