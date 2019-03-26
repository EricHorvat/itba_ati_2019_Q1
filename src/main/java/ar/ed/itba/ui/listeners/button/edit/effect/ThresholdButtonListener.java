package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.ImageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThresholdButtonListener implements ActionListener {
	private final JTextField tField;

	public ThresholdButtonListener(JTextField tField) {
		this.tField = tField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		final int threshold = Integer.parseInt(tField.getText());
		EditableImageFrame editableImageFrame = EditableImageFrame.instance();
		ImageUtils.threshold(editableImageFrame.getAtiImage(), threshold);
		editableImageFrame.buildAndShow();
	}
}
