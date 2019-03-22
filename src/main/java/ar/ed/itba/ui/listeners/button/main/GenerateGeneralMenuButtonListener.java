package ar.ed.itba.ui.listeners.button.main;

import ar.ed.itba.ui.components.MenuButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateGeneralMenuButtonListener extends ATIMenuButtonListener {
	
	public GenerateGeneralMenuButtonListener() {
		options.add(MenuButtonFactory.generateCircleMenuButton());
		options.add(MenuButtonFactory.generateSquareMenuButton());
		options.add(MenuButtonFactory.generateGrayDegradeMenuButton());
		options.add(MenuButtonFactory.generateColorDegradeMenuButton());
		options.add(MenuButtonFactory.generateHSVMenuButton());
	}
	
}
