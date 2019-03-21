package ar.ed.itba.ui.frames.interfaces;

import ar.ed.itba.ui.listeners.mouse.EditableImageMouseAdapter;

import javax.swing.*;
import java.awt.event.MouseAdapter;

public class EditableImageInterface extends ImageInterface {
	private JLabel imageLabel;
	private JPanel mainPanel;
	private JLabel infoLabel;
	private JLabel extraDataLabel;
	private EditableImageMouseAdapter mouseAdapter;
	
	public JPanel getMainPanel(){
		return mainPanel;
	}
	
	public JLabel getImageLabel() {
		return imageLabel;
	}
	
	public EditableImageInterface(){
		setListeners();
	}
	
	private void setListeners(){
		mouseAdapter = new EditableImageMouseAdapter();
		imageLabel.addMouseListener(mouseAdapter);
		imageLabel.addMouseMotionListener(mouseAdapter);
		//fileButton.addActionListener(new FileMenuButtonListener(mainPanel, detailPanel));
		//filterButton.addActionListener(new FiltersMenuButtonListener(mainPanel, detailPanel));
	}
	
	public void setInfo(String s){
		infoLabel.setText(s);
	}
	
	public EditableImageMouseAdapter getEditableMouseAdapter() {
		return mouseAdapter;
	}
}
