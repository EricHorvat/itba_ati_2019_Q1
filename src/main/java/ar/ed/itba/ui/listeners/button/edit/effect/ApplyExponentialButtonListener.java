package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.ui.components.DialogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyExponentialButtonListener implements ActionListener {
	
	private final JTextField exponentialField;
	
	public ApplyExponentialButtonListener(JTextField exponentialField) {
		this.exponentialField = exponentialField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		DialogFactory.notImplementedDialog();
	}
}
