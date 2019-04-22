package ar.ed.itba.ui.listeners.button.filter.menu.tp2.laplacian;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.filter.menu.MaskFilterMenuButtonListener;

import javax.swing.*;

public class LoGFilterMenuButtonListener extends MaskFilterMenuButtonListener {
	
	public LoGFilterMenuButtonListener() {
		options.add(new JLabel("Sigma"));
		JTextField sigmaField = new JTextField();
		options.add(sigmaField);
		options.add(new JLabel("Threshold"));
		JTextField thresholdField = new JTextField();
		options.add(thresholdField);
		options.add(MenuOptionButtonFactory.logFilterMenuOptionButton(maskSideField,sigmaField,thresholdField));
	}
}
