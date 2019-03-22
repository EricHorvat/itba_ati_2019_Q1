package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class ApplyGaussianMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public ApplyGaussianMenuButtonListener() {
		options.add(new JLabel("Sigma"));
		JTextField sigmaField = new JTextField();
		options.add(sigmaField);
		options.add(new JLabel("Mu"));
		JTextField muField = new JTextField();
		options.add(muField);
		options.add(MenuOptionButtonFactory.applyGaussianMenuOptionButton(sigmaField, muField));
	}
	
}
