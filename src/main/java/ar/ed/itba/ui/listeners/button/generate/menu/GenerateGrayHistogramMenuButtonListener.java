package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

public class GenerateGrayHistogramMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateGrayHistogramMenuButtonListener() {
		options.add(MenuOptionButtonFactory.generateGrayHistogramMenuOptionButton());
	}
}
