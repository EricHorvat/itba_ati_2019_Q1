package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.ui.components.DialogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EqualizationButtonListener implements ActionListener {
	
	public EqualizationButtonListener() {
	}
	
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		EditableImageFrame editableImageFrame = EditableImageFrame.instance();
		editableImageFrame.setAtiImage(ImageUtils.equalize(editableImageFrame.getAtiImage()));
		editableImageFrame.buildAndShow();
		new GrayHistogramFrame(editableImageFrame.getAtiImage()).buildAndShow();
	}
}
