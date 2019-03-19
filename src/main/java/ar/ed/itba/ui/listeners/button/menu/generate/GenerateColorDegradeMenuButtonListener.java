package ar.ed.itba.ui.listeners.button.menu.generate;

import ar.ed.itba.ui.components.ButtonFactory;
import ar.ed.itba.ui.listeners.button.menu.ATIMenuButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateColorDegradeMenuButtonListener extends ATIMenuButtonListener {
	
	private final List<JComponent> options = new ArrayList<>();
	
	@Override
	public List<JComponent> getOptions() {
		return options;
	}
	
	public GenerateColorDegradeMenuButtonListener(JPanel targetPanel) {
		super(targetPanel);
		options.add(new JLabel("Color 1"));
		JTextField colorParam1 = new JTextField();
		options.add(colorParam1);
		options.add(new JLabel("Color 2"));
		JTextField colorParam2 = new JTextField();
		options.add(colorParam2);
		options.add(new JLabel("Width"));
		JTextField widthParam = new JTextField();
		options.add(widthParam);
		options.add(new JLabel("Height"));
		JTextField heigthParam = new JTextField();
		options.add(heigthParam);
		options.add(ButtonFactory.generateColorDegradeButton(colorParam1, colorParam2,widthParam,heigthParam));
	}
	
}
