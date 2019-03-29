package ar.ed.itba.ui.listeners.button.filter.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

public class PrewitFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public PrewitFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.prewitAvgFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.prewitMaxFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.prewitMinFilterMenuOptionButton());
	}
}
