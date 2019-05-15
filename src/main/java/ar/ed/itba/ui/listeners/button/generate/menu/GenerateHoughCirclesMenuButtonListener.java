package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

public class GenerateHoughCirclesMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateHoughCirclesMenuButtonListener() {
		options.add(MenuOptionButtonFactory.generateHoughCirclesMenuOptionButton());
	}
	
}
