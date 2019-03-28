package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.CheckUIUtils;
import ar.ed.itba.utils.ImageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicRangeButtonListener implements ActionListener {
	
	final JTextField scalarField;
	
	public DynamicRangeButtonListener(JTextField scalarField) {
		this.scalarField = scalarField;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		double scalar = Double.parseDouble(scalarField.getText());
		if (CheckUIUtils.checkEditableImageVisible()) {
			EditableImageFrame editableImageFrame = EditableImageFrame.instance();
			ATIImage rgbImage = editableImageFrame.getAtiImage();
			editableImageFrame.setAtiImage(ImageUtils.multiply(rgbImage,scalar));
			editableImageFrame.buildAndShow();
		}
	}
}
