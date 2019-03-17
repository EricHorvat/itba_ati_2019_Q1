package ar.ed.itba.ui.listeners.button.menu.generate;

import ar.ed.itba.ui.components.ButtonFactory;
import ar.ed.itba.ui.listeners.button.menu.ATIMenuButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateSquareMenuButtonListener extends ATIMenuButtonListener {
	
	private final List<JComponent> options = new ArrayList<>();
	
	@Override
	public List<JComponent> getOptions() {
		return options;
	}
	
	public GenerateSquareMenuButtonListener(JPanel targetPanel) {
		super(targetPanel);
		options.add(new JLabel("Side"));
		JTextField sideParam = new JTextField();
		options.add(sideParam);
		options.add(ButtonFactory.generateSquareButton(sideParam));
	}
	
}
