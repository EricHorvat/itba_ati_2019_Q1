package ar.ed.itba.ui.listeners.button.finall.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class FinalMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public FinalMenuButtonListener() {
    options.add(MenuOptionButtonFactory.finalMenuOptionButton());
	}
	
}
