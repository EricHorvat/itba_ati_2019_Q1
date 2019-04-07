package ar.ed.itba.ui.listeners.button.filter.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;

public class LoGFilterMenuButtonListener extends MaskFilterMenuButtonListener {
	
	public LoGFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.logFilterMenuOptionButton(maskSideField));
	}
}
