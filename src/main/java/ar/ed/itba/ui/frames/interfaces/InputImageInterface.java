package ar.ed.itba.ui.frames.interfaces;

import ar.ed.itba.ui.listeners.mouse.EditableImageMouseAdapter;

import javax.swing.*;
import java.awt.event.MouseAdapter;

public class InputImageInterface {
	private JLabel imageLabel;
	private JPanel mainPanel;
	private JLabel infoLabel;
	
	public JPanel getMainPanel(){
		return mainPanel;
	}
	
	public JLabel getImageLabel() {
		return imageLabel;
	}
	
	public InputImageInterface(){
		setListeners();
	}
	
	private void setListeners(){
		MouseAdapter m = new EditableImageMouseAdapter();
		imageLabel.addMouseListener(m);
		imageLabel.addMouseMotionListener(m);
		//fileButton.addActionListener(new FileMenuButtonListener(mainPanel, detailPanel));
		//filterButton.addActionListener(new FiltersMenuButtonListener(mainPanel, detailPanel));
	}
	
	public void setInfo(String s){
		infoLabel.setText(s);
	}
	
}
