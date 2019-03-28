package ar.ed.itba.ui.listeners.button.file.effect;

import ar.ed.itba.file.ImageOpener;
import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.OriginalImageFrame;
import ar.ed.itba.utils.CheckUIUtils;
import ar.ed.itba.utils.ImageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetFileButtonListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
		if(CheckUIUtils.checkEditableImageVisible()){
			EditableImageFrame inputImageFrame = EditableImageFrame.instance();
			inputImageFrame.setAtiImage(OriginalImageFrame.instance().getAtiImage());
			inputImageFrame.buildAndShow();
		}
	}
}
