package ar.ed.itba.ui.listeners.button;

import ar.ed.itba.file.ImageOpener;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.ui.frames.ImageFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class OpenFileButtonListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		ImageOpener imageOpener = new ImageOpener();
		BufferedImage image = imageOpener.open();
		
		EditableImageFrame inputImageFrame = EditableImageFrame.instance();
		inputImageFrame.setImage(image);
		inputImageFrame.buildAndShow();
		
		FrameFactory.fixedImageFrame("originalImage", image).buildAndShow();
	}
}
