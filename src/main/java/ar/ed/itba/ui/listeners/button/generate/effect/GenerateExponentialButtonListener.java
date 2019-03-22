package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.ui.components.DialogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateExponentialButtonListener implements ActionListener {
	
	private final JTextField exponentalField;
	
	public GenerateExponentialButtonListener(JTextField exponentalField) {
		this.exponentalField = exponentalField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		DialogFactory.notImplementedDialog();
	}
}
