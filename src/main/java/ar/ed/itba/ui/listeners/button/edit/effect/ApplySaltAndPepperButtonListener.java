package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.ui.components.DialogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplySaltAndPepperButtonListener implements ActionListener {
	
	private final JTextField pField;
	
	public ApplySaltAndPepperButtonListener(JTextField pField) {
		this.pField = pField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		DialogFactory.notImplementedDialog();
	}
}