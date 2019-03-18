package ar.ed.itba.ui.listeners.button;

import ar.ed.itba.file.image.PbmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateCircleButtonListener implements ActionListener {
	
	final JTextField radiusParam;
	
	public GenerateCircleButtonListener(JTextField radiusParam) {
		this.radiusParam = radiusParam;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		PbmImage image = PbmImage.createWhiteCircle(Integer.parseInt(radiusParam.getText()));
		
		EditableImageFrame inputImageFrame = EditableImageFrame.instance();
		inputImageFrame.setAtiImage(image);
		inputImageFrame.buildAndShow();
	}
}
