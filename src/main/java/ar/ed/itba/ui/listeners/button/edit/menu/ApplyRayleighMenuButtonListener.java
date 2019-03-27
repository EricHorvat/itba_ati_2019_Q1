package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class ApplyRayleighMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public ApplyRayleighMenuButtonListener() {
		options.add(new JLabel("Phi"));
		JTextField phiField = new JTextField();
		options.add(phiField);
		options.add(new JLabel("Percentage"));
		JTextField percentageField = new JTextField();
		options.add(percentageField);
		options.add(MenuOptionButtonFactory.applyRayleighMenuOptionButton(percentageField,phiField));
	}
	
}
