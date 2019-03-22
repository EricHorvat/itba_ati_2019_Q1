package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateSquareGeneralMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateSquareGeneralMenuButtonListener() {
		options.add(new JLabel("Side"));
		JTextField sideParam = new JTextField();
		options.add(sideParam);
		options.add(MenuOptionButtonFactory.generateSquareMenuOptionButton(sideParam));
	}
	
}
