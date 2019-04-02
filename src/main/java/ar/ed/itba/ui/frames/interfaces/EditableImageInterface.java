package ar.ed.itba.ui.frames.interfaces;

import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.listeners.mouse.EditableImageMouseAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class EditableImageInterface extends ImageInterface {
	private JLabel imageLabel;
	private JPanel mainPanel;
	private JLabel infoLabel;
	private JLabel extraDataLabel;
	private JButton redoButton;
	private JButton undoButton;
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
		undoButton.addActionListener(new UndoListener());
		redoButton.addActionListener(new RedoListener());
		//fileButton.addActionListener(new FileGeneralMenuButtonListener(mainPanel, detailPanel));
		//filterButton.addActionListener(new FiltersGeneralMenuButtonListener(mainPanel, detailPanel));
	}
	
	public void setInfo(String s){
		infoLabel.setText(s);
	}
	
	public EditableImageMouseAdapter getEditableMouseAdapter() {
		return mouseAdapter;
	}
	
	private class RedoListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			EditableImageFrame.instance().redo();
		}
	}
	
	private class UndoListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			EditableImageFrame.instance().undo();
		}
	}
}
