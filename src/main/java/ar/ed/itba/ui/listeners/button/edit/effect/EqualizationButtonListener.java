package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.OriginalImageFrame;
import ar.ed.itba.ui.frames.histogram.GrayHistogramFrame;
import ar.ed.itba.utils.CheckUIUtils;
import ar.ed.itba.utils.ImageUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EqualizationButtonListener implements ActionListener {
	
	public EqualizationButtonListener() {
	}
	
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if(CheckUIUtils.checkEditableImageVisible()) {
			EditableImageFrame editableImageFrame = EditableImageFrame.instance();
			editableImageFrame.setAtiImage(ImageUtils.equalize(editableImageFrame.getAtiImage()));
			editableImageFrame.buildAndShow();
			new GrayHistogramFrame("Original gray histogram",OriginalImageFrame.instance().getAtiImage()).buildAndShow();
			new GrayHistogramFrame("Result gray histogram",editableImageFrame.getAtiImage()).buildAndShow();
		}
	}
}
