package ar.ed.itba.ui.listeners.button.main;

import ar.ed.itba.ui.components.MenuButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuButtonListener;

public class FiltersGeneralMenuButtonListener extends ATIMenuButtonListener {
	
	public FiltersGeneralMenuButtonListener() {
		options.add(MenuButtonFactory.mediaFilterMenuButton());
		options.add(MenuButtonFactory.medianFilterMenuButton());
		options.add(MenuButtonFactory.ponderedMedianFilterMenuButton());
		options.add(MenuButtonFactory.gaussianFilterMenuButton());
		options.add(MenuButtonFactory.highlightBorderFilterMenuButton());
		options.add(MenuButtonFactory.prewitFilterMenuButton());
		options.add(MenuButtonFactory.sobelFilterMenuButton());
		options.add(MenuButtonFactory.kirshFilterMenuButton());
		options.add(MenuButtonFactory.tp2p5aFilterMenuButton());
	}
	
}
