package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateGrayDegradeGeneralMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateGrayDegradeGeneralMenuButtonListener() {
		options.add(new JLabel("Width"));
		JTextField widthParam = new JTextField();
		options.add(widthParam);
		options.add(new JLabel("Height"));
		JTextField heigthParam = new JTextField();
		options.add(heigthParam);
		options.add(MenuOptionButtonFactory.generateGrayDegradeMenuOptionButton(widthParam,heigthParam));
	}
	
}
