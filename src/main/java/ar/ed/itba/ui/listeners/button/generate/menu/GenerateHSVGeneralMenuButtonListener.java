package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateHSVGeneralMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateHSVGeneralMenuButtonListener() {
		options.add(MenuOptionButtonFactory.generateHSVMenuOptionButton());
	}
	
}
