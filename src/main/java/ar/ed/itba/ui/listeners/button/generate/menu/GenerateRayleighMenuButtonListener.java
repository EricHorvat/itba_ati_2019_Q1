package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class GenerateRayleighMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateRayleighMenuButtonListener() {
		options.add(new JLabel("Percentage"));
		JTextField percentageField = new JTextField();
		options.add(percentageField);
		options.add(new JLabel("Phi"));
		JTextField phiField = new JTextField();
		options.add(phiField);
		options.add(MenuOptionButtonFactory.generateRayleighMenuOptionButton(percentageField,phiField));
	}
	
}
