package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class GenerateExponentialMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateExponentialMenuButtonListener() {
		options.add(new JLabel("Lambda"));
		JTextField lambfaField = new JTextField();
		options.add(lambfaField);
		options.add(MenuOptionButtonFactory.generateExponentialMenuOptionButton(lambfaField));
	}
	
}
