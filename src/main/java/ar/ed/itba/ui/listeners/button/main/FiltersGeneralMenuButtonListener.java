package ar.ed.itba.ui.listeners.button.main;

import ar.ed.itba.ui.components.MenuButtonFactory;
import ar.ed.itba.ui.components.MenuOptionButtonFactory;
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
		options.add(MenuButtonFactory.laplacianFilterMenuButton());
		options.add(MenuButtonFactory.logFilterMenuButton());
		options.add(MenuButtonFactory.bilateralFilterMenuButton());
		options.add(MenuButtonFactory.anisotropicFilterMenuButton());
    options.add(MenuButtonFactory.isotropicFilterMenuButton());
    options.add(MenuButtonFactory.cannyFilterMenuButton());
    options.add(MenuButtonFactory.susanFilterMenuButton());
    options.add(MenuButtonFactory.activecontourFilterMenuButton());
    options.add(MenuButtonFactory.harrisFilterMenuButton());
	}
	
}
