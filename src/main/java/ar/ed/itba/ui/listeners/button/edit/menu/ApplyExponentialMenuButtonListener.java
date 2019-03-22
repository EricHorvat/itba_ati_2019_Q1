package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class ApplyExponentialMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public ApplyExponentialMenuButtonListener() {
		options.add(new JLabel("Lambda"));
		JTextField lambfaField = new JTextField();
		options.add(lambfaField);
		options.add(MenuOptionButtonFactory.applyExponentialMenuOptionButton(lambfaField));
	}
	
}
