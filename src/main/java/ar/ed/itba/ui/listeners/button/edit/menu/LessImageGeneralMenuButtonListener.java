package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class LessImageGeneralMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public LessImageGeneralMenuButtonListener() {
		options.add(new JLabel("Image"));
		JTextField filePathParam = new JTextField();
		options.add(filePathParam);
		options.add(MenuOptionButtonFactory.lessImageMenuOptionButton(filePathParam));
	}
}
