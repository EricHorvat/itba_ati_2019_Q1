package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PgmImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateHSVButtonListener implements ActionListener {
	
	public GenerateHSVButtonListener() {}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
		
		PpmImage ppmImage = (PpmImage) EditableImageFrame.instance().getAtiImage();
		
		PgmImage[] images = ppmImage.toHSV();
		FrameFactory.fixedImageFrame("Hue", images[0]).buildAndShow();
		FrameFactory.fixedImageFrame("Saturation", images[1]).buildAndShow();
		FrameFactory.fixedImageFrame("Value", images[2]).buildAndShow();
	}
}
