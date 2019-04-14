package ar.ed.itba.ui.listeners.button.filter.menu.tp2.gradient;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

public class PrewitFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public PrewitFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.prewitAvgFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.prewitMaxFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.prewitMinFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.prewitHorizontalFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.prewitVerticalFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.prewitMod2FilterMenuOptionButton());
	}
}
