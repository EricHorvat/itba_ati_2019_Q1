package ar.ed.itba.ui.listeners.button.filter.menu.tp1;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.filter.menu.MaskFilterMenuButtonListener;

public class GaussianFilterMenuButtonListener extends MaskFilterMenuButtonListener {
	
	public GaussianFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.gaussianFilterMenuOptionButton(maskSideField));
	}
}
