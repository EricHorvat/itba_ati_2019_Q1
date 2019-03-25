package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.noise.SaltAndPepper;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplySaltAndPepperButtonListener implements ActionListener {
	
	private final JTextField cField;
	private final JTextField sField;
	
	public ApplySaltAndPepperButtonListener(JTextField cField, JTextField sField) {
		this.cField = cField;
		this.sField = sField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		final double contamination = Double.parseDouble(cField.getText());
		final double saltProb = Double.parseDouble(sField.getText());
		EditableImageFrame editableImageFrame = EditableImageFrame.instance();
		editableImageFrame.setAtiImage(SaltAndPepper.generateNoise(editableImageFrame.getAtiImage(), contamination, saltProb));
	}
}
