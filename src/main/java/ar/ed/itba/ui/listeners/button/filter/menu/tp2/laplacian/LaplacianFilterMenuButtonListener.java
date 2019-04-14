package ar.ed.itba.ui.listeners.button.filter.menu.tp2.laplacian;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class LaplacianFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public LaplacianFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.laplacianFilterMenuOptionButton());
		options.add(new JLabel("Threshold"));
		JTextField thresholdField = new JTextField();
		options.add(thresholdField);
		options.add(MenuOptionButtonFactory.laplacianPendientFilterMenuOptionButton(thresholdField));
	}
}
