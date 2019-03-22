package ar.ed.itba.ui.listeners.button.filter.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

public class HighlightBorderFilterMenuButtonListener extends MaskFilterMenuButtonListener {
	
	public HighlightBorderFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.highlightBorderFilterMenuOptionButton(maskSideField));
	}
}
