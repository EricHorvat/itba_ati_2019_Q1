package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DynamicRangeGeneralMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public DynamicRangeGeneralMenuButtonListener() {
		options.add(MenuOptionButtonFactory.dynamicRangeMenuOptionButton());
	}
}
