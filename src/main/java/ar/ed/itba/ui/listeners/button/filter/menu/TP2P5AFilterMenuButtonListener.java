package ar.ed.itba.ui.listeners.button.filter.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

public class TP2P5AFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public TP2P5AFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.tp2p5aAvgFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.tp2p5aMaxFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.tp2p5aMinFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.tp2p5aHorizontalFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.tp2p5aVerticalFilterMenuOptionButton());
		options.add(MenuOptionButtonFactory.tp2p5aMod2FilterMenuOptionButton());
	}
}
