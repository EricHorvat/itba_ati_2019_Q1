package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PgmImage;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateGaussianButtonListener implements ActionListener {
	
	private final JTextField sigmaField;
	private final JTextField muField;
	
	public GenerateGaussianButtonListener(JTextField sigmaField, JTextField muField) {
		this.sigmaField = sigmaField;
		this.muField = muField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		DialogFactory.notImplementedDialog();
	}
}
