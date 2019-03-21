package ar.ed.itba.ui.listeners.button;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.ImageOpener;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.ui.frames.OriginalImageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFileButtonListener implements ActionListener {
	
	private final JTextField filePathField;
	
	public OpenFileButtonListener(JTextField filePathField) {
		this.filePathField = filePathField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		ImageOpener imageOpener = new ImageOpener();
		ATIImage image = imageOpener.open(filePathField.getText());
		
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
