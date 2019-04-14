package ar.ed.itba.ui.listeners.button.filter.menu.tp1;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

public class PonderedMedianFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public PonderedMedianFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.ponderedMedianFilterMenuOptionButton());
	}
}
