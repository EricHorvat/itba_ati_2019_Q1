package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.ui.components.DialogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighlightBorderFilterButtonListener extends MaskFilterButtonListener{
	
	public HighlightBorderFilterButtonListener(JTextField maskSideField) {
		super(maskSideField);
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		DialogFactory.notImplementedDialog();
	}
}