package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class GenerateExponentialMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateExponentialMenuButtonListener() {
		options.add(new JLabel("Percentage"));
		JTextField percentageField = new JTextField();
		options.add(percentageField);
		options.add(new JLabel("Lambda"));
		JTextField lambdaField = new JTextField();
		options.add(lambdaField);
		options.add(MenuOptionButtonFactory.generateExponentialMenuOptionButton(percentageField,lambdaField));
	}
	
}
