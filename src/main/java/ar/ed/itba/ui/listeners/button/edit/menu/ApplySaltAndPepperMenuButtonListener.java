package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class ApplySaltAndPepperMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public ApplySaltAndPepperMenuButtonListener() {
		options.add(new JLabel("P"));
		JTextField pField = new JTextField();
		options.add(pField);
		options.add(MenuOptionButtonFactory.applySaltAndPepperMenuOptionButton(pField));
	}
	
}
