package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateCircleGeneralMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateCircleGeneralMenuButtonListener() {
		options.add(new JLabel("Radius"));
		JTextField radiusParam = new JTextField();
		options.add(radiusParam);
		options.add(MenuOptionButtonFactory.generateCircleMenuOptionButton(radiusParam));
	}
	
}
