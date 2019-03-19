package ar.ed.itba.ui.listeners.button;

import ar.ed.itba.file.image.PbmImage;
import ar.ed.itba.file.image.PgmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateGrayDegradeButtonListener implements ActionListener {
	
	final JTextField widthField;
	final JTextField heightField;
	
	public GenerateGrayDegradeButtonListener(JTextField widthField, JTextField heightField) {
		this.widthField= widthField;
		this.heightField= heightField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		PgmImage image = PgmImage.createGrayDowngrade(Integer.parseInt(widthField.getText()), Integer.parseInt(heightField.getText()));
		
		EditableImageFrame inputImageFrame = EditableImageFrame.instance();
		inputImageFrame.setAtiImage(image);
		inputImageFrame.buildAndShow();
	}
}
