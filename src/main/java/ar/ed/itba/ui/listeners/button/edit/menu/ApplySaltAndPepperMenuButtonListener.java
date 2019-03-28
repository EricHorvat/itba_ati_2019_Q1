package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class ApplySaltAndPepperMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public ApplySaltAndPepperMenuButtonListener() {
		options.add(new JLabel("Salt"));
		JTextField sField = new JTextField();
		options.add(sField);
		options.add(MenuOptionButtonFactory.applySaltAndPepperMenuOptionButton(sField));
	}
	
}
