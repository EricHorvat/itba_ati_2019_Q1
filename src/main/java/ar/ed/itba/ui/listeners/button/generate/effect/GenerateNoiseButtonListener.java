package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PgmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.histogram.GrayHistogramFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public abstract class GenerateNoiseButtonListener implements ActionListener {
	
	protected abstract BufferedImage getImage();
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
		BufferedImage bi = getImage();
		
		EditableImageFrame inputImageFrame = EditableImageFrame.instance();
		inputImageFrame.setAtiImage(new PgmImage(bi));
		inputImageFrame.buildAndShow();
		
		new GrayHistogramFrame(inputImageFrame.getAtiImage(),true).buildAndShow();
		
	}
}
