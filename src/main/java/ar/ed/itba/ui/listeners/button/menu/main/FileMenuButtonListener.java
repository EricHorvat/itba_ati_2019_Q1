package ar.ed.itba.ui.listeners.button.menu.main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import ar.ed.itba.ui.components.ButtonFactory;
import ar.ed.itba.ui.components.MenuButtonFactory;
import ar.ed.itba.ui.listeners.button.menu.ATIMenuButtonListener;

public class FileMenuButtonListener extends ATIMenuButtonListener {
	
	private final List<JButton> options = new ArrayList<>();
	
	@Override
	public List<JButton> getOptions() {
		return options;
	}
	
	public FileMenuButtonListener(JPanel targetPanel) {
		super(targetPanel);
		options.add(MenuButtonFactory.openFileMenuButton());
	}
	
}
