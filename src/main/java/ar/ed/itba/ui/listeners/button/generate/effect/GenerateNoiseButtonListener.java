package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.histogram.GrayHistogramFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GenerateNoiseButtonListener implements ActionListener {
	
	protected final JTextField percentageField;
	
	protected abstract int[] getImage(int width, int height);
	
	public GenerateNoiseButtonListener(JTextField percentageField) {
		this.percentageField = percentageField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
		int[] intArray = getImage(100,100);
		
		EditableImageFrame inputImageFrame = EditableImageFrame.instance();
		inputImageFrame.setAtiImage(new PpmImage(intArray,100,100));
		inputImageFrame.buildAndShow();
		
		new GrayHistogramFrame(inputImageFrame.getAtiImage(),true).buildAndShow();
		
	}
}
