package ar.ed.itba.ui.listeners.button.file.effect;

import ar.ed.itba.file.ImageOpener;
import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.OriginalImageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFixedFileButtonListener implements ActionListener {
	
	private final String fixedFile;
	
	public OpenFixedFileButtonListener(String fixedFile) {
		this.fixedFile = fixedFile;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		ImageOpener imageOpener = new ImageOpener();
		ATIImage image = imageOpener.open(fixedFile);
		if(image != null){
			EditableImageFrame inputImageFrame = EditableImageFrame.instance();
			inputImageFrame.setAtiImage(image);
			inputImageFrame.buildAndShow();
			
			OriginalImageFrame originalImageFrame = OriginalImageFrame.instance();
			originalImageFrame.setAtiImage(image);
			originalImageFrame.buildAndShow();
		}
	}
}
