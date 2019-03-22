package ar.ed.itba.ui.listeners.button.filter.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

public class MedianFilterMenuButtonListener extends MaskFilterMenuButtonListener {
	
	public MedianFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.medianFilterMenuOptionButton(maskSideField));
	}
}
