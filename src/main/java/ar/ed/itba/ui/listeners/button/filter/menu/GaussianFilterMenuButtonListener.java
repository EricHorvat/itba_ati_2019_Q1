package ar.ed.itba.ui.listeners.button.filter.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;
import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;

import javax.swing.*;

public class GaussianFilterMenuButtonListener extends MaskFilterMenuButtonListener {
	
	public GaussianFilterMenuButtonListener() {
		options.add(new JLabel("Sigma"));
		JTextField sigmaField = new JTextField();
		options.add(sigmaField);
		options.add(MenuOptionButtonFactory.gaussianFilterMenuOptionButton(maskSideField,sigmaField));
	}
}
