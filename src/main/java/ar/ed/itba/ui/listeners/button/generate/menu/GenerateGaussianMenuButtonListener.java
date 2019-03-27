package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class GenerateGaussianMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateGaussianMenuButtonListener() {
		options.add(new JLabel("Percentage"));
		JTextField percentageField = new JTextField();
		options.add(percentageField);
		options.add(new JLabel("Sigma"));
		JTextField sigmaField = new JTextField();
		options.add(sigmaField);
		options.add(new JLabel("Mu"));
		JTextField muField = new JTextField();
		options.add(muField);
		options.add(MenuOptionButtonFactory.generateGaussianMenuOptionButton(percentageField, sigmaField, muField));
	}
	
}
