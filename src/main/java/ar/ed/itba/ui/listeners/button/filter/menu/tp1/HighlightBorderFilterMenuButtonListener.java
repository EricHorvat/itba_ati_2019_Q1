package ar.ed.itba.ui.listeners.button.filter.menu.tp1;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;
import ar.ed.itba.ui.listeners.button.filter.menu.MaskFilterMenuButtonListener;

public class HighlightBorderFilterMenuButtonListener extends MaskFilterMenuButtonListener {
	
	public HighlightBorderFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.highlightBorderFilterMenuOptionButton(maskSideField));
	}
}
