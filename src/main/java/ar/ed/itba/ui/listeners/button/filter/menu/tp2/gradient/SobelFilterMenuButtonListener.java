package ar.ed.itba.ui.listeners.button.filter.menu.tp2.gradient;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

public class SobelFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public SobelFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.sobelAvgFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.sobelMaxFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.sobelMinFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.sobelHorizontalFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.sobelVerticalFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.sobelMod2FilterMenuOptionButton());
	}
}
