package ar.ed.itba.ui.listeners.button.menu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import ar.ed.itba.ui.components.ButtonFactory;

public class FileMenuButtonListener extends ATIMenuButtonListener {
	
	private final List<JButton> options = new ArrayList<>();
	
	@Override
	List<JButton> getOptions() {
		return options;
	}
	
	public FileMenuButtonListener(JPanel originPanel, JPanel targetPanel) {
		super(originPanel, targetPanel);
		options.add(ButtonFactory.openFileButton());
	}
	
}
