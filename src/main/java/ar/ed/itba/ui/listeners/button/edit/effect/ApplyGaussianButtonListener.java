package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.ui.components.DialogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyGaussianButtonListener implements ActionListener {
	
	private final JTextField sigmaField;
	private final JTextField muField;
	
	public ApplyGaussianButtonListener(JTextField sigmaField, JTextField muField) {
		this.sigmaField = sigmaField;
		this.muField = muField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		DialogFactory.notImplementedDialog();
	}
}
