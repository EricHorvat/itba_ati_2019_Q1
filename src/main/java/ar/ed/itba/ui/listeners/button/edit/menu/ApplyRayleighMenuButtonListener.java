package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class ApplyRayleighMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public ApplyRayleighMenuButtonListener() {
		options.add(new JLabel("Xi"));
		JTextField xiField = new JTextField();
		options.add(xiField);
		options.add(MenuOptionButtonFactory.applyRayleighMenuOptionButton(xiField));
	}
	
}
