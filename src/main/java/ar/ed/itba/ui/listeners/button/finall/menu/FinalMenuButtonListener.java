package ar.ed.itba.ui.listeners.button.finall.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class FinalMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public FinalMenuButtonListener() {
    JCheckBox runAllCheckBox = new JCheckBox("Run all");
    options.add(runAllCheckBox);
    JCheckBox ignoreNeighbours = new JCheckBox("Ignore Neighbours");
    options.add(ignoreNeighbours);
    JCheckBox withDelta = new JCheckBox("With Delta",true);
    options.add(withDelta);
    options.add(MenuOptionButtonFactory.finalMenuOptionButton(runAllCheckBox, ignoreNeighbours, withDelta));
	}
	
}
