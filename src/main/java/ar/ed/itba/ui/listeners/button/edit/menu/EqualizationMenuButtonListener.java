package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EqualizationMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public EqualizationMenuButtonListener() {
		options.add(MenuOptionButtonFactory.equalizationMenuOptionButton());
	}
}
