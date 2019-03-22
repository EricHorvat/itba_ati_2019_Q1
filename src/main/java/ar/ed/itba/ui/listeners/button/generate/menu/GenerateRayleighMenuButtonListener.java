package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class GenerateRayleighMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateRayleighMenuButtonListener() {
		options.add(new JLabel("Xi"));
		JTextField xiField = new JTextField();
		options.add(xiField);
		options.add(MenuOptionButtonFactory.generateRayleighMenuOptionButton(xiField));
	}
	
}
