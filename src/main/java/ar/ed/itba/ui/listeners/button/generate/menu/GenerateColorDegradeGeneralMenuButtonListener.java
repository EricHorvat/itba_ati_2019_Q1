package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateColorDegradeGeneralMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateColorDegradeGeneralMenuButtonListener() {
		options.add(new JLabel("Color 1"));
		JTextField colorParam1 = new JTextField();
		options.add(colorParam1);
		options.add(new JLabel("Color 2"));
		JTextField colorParam2 = new JTextField();
		options.add(colorParam2);
		options.add(new JLabel("Width"));
		JTextField widthParam = new JTextField();
		options.add(widthParam);
		options.add(new JLabel("Height"));
		JTextField heigthParam = new JTextField();
		options.add(heigthParam);
		options.add(MenuOptionButtonFactory.generateColorDegradeMenuOptionButton(colorParam1, colorParam2,widthParam,heigthParam));
	}
	
}
