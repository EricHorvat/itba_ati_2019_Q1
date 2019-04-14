package ar.ed.itba.ui.listeners.button.filter.menu.tp2.gradient;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

public class KirshFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public KirshFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.kirshAvgFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.kirshMaxFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.kirshMinFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.kirshHorizontalFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.kirshVerticalFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.kirshMod2FilterMenuOptionButton());
	}
}
