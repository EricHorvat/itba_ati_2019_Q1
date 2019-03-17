package ar.ed.itba.ui.listeners.button;

import ar.ed.itba.file.PbmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateSquareButtonListener implements ActionListener {
	
	final JTextField sideTextField;
	
	public GenerateSquareButtonListener(JTextField sideTextField) {
		this.sideTextField = sideTextField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		PbmImage image = PbmImage.createWhiteSquare(Integer.parseInt(sideTextField.getText()));
		
		EditableImageFrame inputImageFrame = EditableImageFrame.instance();
		inputImageFrame.setImage(image.view());
		inputImageFrame.buildAndShow();
		
	}
}
