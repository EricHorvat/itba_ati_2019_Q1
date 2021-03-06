package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.noise.SaltAndPepper;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.CheckUIUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplySaltAndPepperButtonListener implements ActionListener {
	
	private final JTextField sField;
	
	public ApplySaltAndPepperButtonListener(JTextField sField) {
		this.sField = sField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (CheckUIUtils.checkEditableImageVisible()) {
			final double saltProb = Double.parseDouble(sField.getText());
			EditableImageFrame editableImageFrame = EditableImageFrame.instance();
			editableImageFrame.setAtiImage(SaltAndPepper.generateNoise(editableImageFrame.getAtiImage(), saltProb));
			editableImageFrame.buildAndShow();
		}
	}
}
