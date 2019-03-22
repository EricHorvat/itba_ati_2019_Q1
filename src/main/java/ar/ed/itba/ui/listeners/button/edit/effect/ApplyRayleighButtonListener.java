package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.ui.components.DialogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyRayleighButtonListener implements ActionListener {
	
	private final JTextField xiField;
	
	public ApplyRayleighButtonListener(JTextField xiField) {
		this.xiField = xiField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		DialogFactory.notImplementedDialog();
	}
}
