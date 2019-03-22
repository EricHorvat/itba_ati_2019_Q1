package ar.ed.itba.ui.listeners.button.filter.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

public class PonderedMedianFilterMenuButtonListener extends MaskFilterMenuButtonListener {
	
	public PonderedMedianFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.ponderedMedianFilterMenuOptionButton(maskSideField));
	}
}
