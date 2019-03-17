package ar.ed.itba.ui.listeners.button.menu.main;

import ar.ed.itba.ui.components.ButtonFactory;
import ar.ed.itba.ui.components.MenuButtonFactory;
import ar.ed.itba.ui.listeners.button.menu.ATIMenuButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateMenuButtonListener extends ATIMenuButtonListener {
	
	private final List<JButton> options = new ArrayList<>();
	
	@Override
	public List<JButton> getOptions() {
		return options;
	}
	
	public GenerateMenuButtonListener(JPanel targetPanel) {
		super(targetPanel);
		options.add(MenuButtonFactory.generateCircleMenuButton());
		options.add(MenuButtonFactory.generateSquareMenuButton());
	}
	
}
