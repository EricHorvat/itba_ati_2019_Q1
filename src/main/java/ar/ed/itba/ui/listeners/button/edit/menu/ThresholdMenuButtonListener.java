package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ThresholdMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public ThresholdMenuButtonListener() {
		options.add(new JLabel("Threshold"));
		JTextField tField = new JTextField();
		options.add(tField);
		options.add(MenuOptionButtonFactory.thresholdMenuOptionButton(tField));
	}
}
