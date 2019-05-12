package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class GenerateHoughLinesMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateHoughLinesMenuButtonListener() {
		options.add(MenuOptionButtonFactory.generateHoughLinesMenuOptionButton());
	}
	
}
