package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddImageGeneralMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public AddImageGeneralMenuButtonListener() {
		options.add(new JLabel("Image"));
		JTextField filePathParam = new JTextField();
		options.add(filePathParam);
		options.add(MenuOptionButtonFactory.addImageMenuOptionButton(filePathParam));
	}
}
