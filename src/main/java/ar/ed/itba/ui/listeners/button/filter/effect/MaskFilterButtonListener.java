package ar.ed.itba.ui.listeners.button.filter.effect;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class MaskFilterButtonListener implements ActionListener {
	
	protected JTextField maskSideField;
	
	public MaskFilterButtonListener(JTextField maskSideField) {
		this.maskSideField = maskSideField;
	}
}
