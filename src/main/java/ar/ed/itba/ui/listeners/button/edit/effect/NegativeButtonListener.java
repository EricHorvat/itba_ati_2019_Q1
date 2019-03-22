package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.ui.components.DialogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NegativeButtonListener implements ActionListener {
	public NegativeButtonListener(JTextField filePathField) {
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		DialogFactory.notImplementedDialog();
	}
}
