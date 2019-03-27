package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.math.NoiseImageFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.ImageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ApplyNoiseButtonListener implements ActionListener {
	
	protected final JTextField percertangeField;
	
	public ApplyNoiseButtonListener(JTextField percertangeField) {
		this.percertangeField = percertangeField;
	}
	
	protected abstract ATIImage applyNoise(ATIImage atiImage, double percentage);
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		double percentage = Double.parseDouble(percertangeField.getText());
		
		EditableImageFrame editableImageFrame = EditableImageFrame.instance();
		ATIImage atiImage = editableImageFrame.getAtiImage();
		
		atiImage = applyNoise(atiImage, percentage);
		
		editableImageFrame.setAtiImage(atiImage);
		editableImageFrame.buildAndShow();
	}
}
