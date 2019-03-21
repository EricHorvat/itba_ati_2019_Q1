package ar.ed.itba.ui.listeners.button.menu.generate;

import ar.ed.itba.ui.components.ButtonFactory;
import ar.ed.itba.ui.listeners.button.menu.ATIMenuButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateHSVMenuButtonListener extends ATIMenuButtonListener {
	
	private final List<JComponent> options = new ArrayList<>();
	
	@Override
	public List<JComponent> getOptions() {
		return options;
	}
	
	public GenerateHSVMenuButtonListener(JPanel targetPanel) {
		super(targetPanel);
		options.add(ButtonFactory.generateHSVButton());
	}
	
}
