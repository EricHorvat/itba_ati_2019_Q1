package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.ImageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GammaPowerButtonListener implements ActionListener {

	private final JTextField gammaField;

	public GammaPowerButtonListener(JTextField gammaField) {
		this.gammaField = gammaField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		final double gamma = Double.parseDouble(gammaField.getText());
		EditableImageFrame editableImageFrame = EditableImageFrame.instance();
		ImageUtils.gammaPower(editableImageFrame.getAtiImage(), gamma);
		editableImageFrame.buildAndShow();
	}
}
