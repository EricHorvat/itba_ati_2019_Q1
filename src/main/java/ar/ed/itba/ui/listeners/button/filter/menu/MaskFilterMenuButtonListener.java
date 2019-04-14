package ar.ed.itba.ui.listeners.button.filter.menu;

import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public abstract class MaskFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
	protected JTextField maskSideField = new JTextField();
	
	protected MaskFilterMenuButtonListener() {
		options.add(new JLabel("Mask Side"));
		options.add(maskSideField);
	}
}
