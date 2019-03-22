package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PbmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;

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
		inputImageFrame.setAtiImage(image);
		inputImageFrame.buildAndShow();
		
	}
}
