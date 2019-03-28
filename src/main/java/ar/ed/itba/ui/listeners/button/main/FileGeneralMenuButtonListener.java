package ar.ed.itba.ui.listeners.button.main;

import ar.ed.itba.ui.components.MenuButtonFactory;
import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuButtonListener;

public class FileGeneralMenuButtonListener extends ATIMenuButtonListener {
	
	public FileGeneralMenuButtonListener() {
		options.add(MenuButtonFactory.openFileMenuButton());
		options.add(MenuButtonFactory.saveFileMenuButton());
		options.add(MenuOptionButtonFactory.resetFileMenuOptionButton());
	}
	
}
