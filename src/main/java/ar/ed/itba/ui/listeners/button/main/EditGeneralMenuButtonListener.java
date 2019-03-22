package ar.ed.itba.ui.listeners.button.main;

import ar.ed.itba.ui.components.MenuButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EditGeneralMenuButtonListener extends ATIMenuButtonListener {
	
	public EditGeneralMenuButtonListener() {
		options.add(MenuButtonFactory.setPixelMenuButton());
		options.add(MenuButtonFactory.setAddMenuButton());
		options.add(MenuButtonFactory.setLessMenuButton());
		options.add(MenuButtonFactory.setDynamicRangeMenuButton());
		options.add(MenuButtonFactory.setGammaPowMenuButton());
		options.add(MenuButtonFactory.setNegativeMenuButton());
		options.add(MenuButtonFactory.setContrastMenuButton());
		options.add(MenuButtonFactory.setThresholdMenuButton());
		options.add(MenuButtonFactory.setEqualizationMenuButton());
		
	}
	
}
