package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EditPixelGeneralMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public EditPixelGeneralMenuButtonListener() {
		options.add(new JLabel("Color"));
		JTextField colorParam = new JTextField();
		options.add(colorParam);
		options.add(MenuOptionButtonFactory.editPixelMenuOptionButton(colorParam));
	}
	
}
