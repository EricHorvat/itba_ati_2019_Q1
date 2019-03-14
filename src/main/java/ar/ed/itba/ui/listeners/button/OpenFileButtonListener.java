package ar.ed.itba.ui.listeners.button;

import ar.ed.itba.file.ImageOpener;
import ar.ed.itba.ui.frames.InputImageFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class OpenFileButtonListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		ImageOpener imageOpener = new ImageOpener();
		BufferedImage image = imageOpener.open();
		InputImageFrame inputImageFrame = InputImageFrame.instance();
		inputImageFrame.setImage(image);
		inputImageFrame.build();
		inputImageFrame.makeShow();
	}
}
