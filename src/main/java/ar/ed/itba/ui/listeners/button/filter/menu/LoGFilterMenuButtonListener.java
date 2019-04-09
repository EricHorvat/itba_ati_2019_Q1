package ar.ed.itba.ui.listeners.button.filter.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;

import javax.swing.*;

public class LoGFilterMenuButtonListener extends MaskFilterMenuButtonListener {
	
	public LoGFilterMenuButtonListener() {
		options.add(new JLabel("Threshold"));
		JTextField thresholdField = new JTextField();
		options.add(thresholdField);
		options.add(MenuOptionButtonFactory.logFilterMenuOptionButton(maskSideField,thresholdField));
	}
}
