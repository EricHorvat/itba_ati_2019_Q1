package ar.ed.itba.ui.listeners.button.filter.menu.tp1;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;
import ar.ed.itba.ui.listeners.button.filter.menu.MaskFilterMenuButtonListener;

public class MedianFilterMenuButtonListener extends MaskFilterMenuButtonListener {
	
	public MedianFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.medianFilterMenuOptionButton(maskSideField));
	}
}
