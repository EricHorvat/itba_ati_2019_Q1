package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.histogram.GrayHistogramFrame;
import ar.ed.itba.utils.CheckUIUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateGrayHistogramButtonListener implements ActionListener {
	public GenerateGrayHistogramButtonListener() {
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if(CheckUIUtils.checkEditableImageVisible()){
			
			new GrayHistogramFrame(EditableImageFrame.instance().getAtiImage()).buildAndShow();
		}
	}
}
