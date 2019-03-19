package ar.ed.itba.ui.listeners.button.menu.generate;

import ar.ed.itba.ui.components.ButtonFactory;
import ar.ed.itba.ui.listeners.button.menu.ATIMenuButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateGrayDegradeMenuButtonListener extends ATIMenuButtonListener {
	
	private final List<JComponent> options = new ArrayList<>();
	
	@Override
	public List<JComponent> getOptions() {
		return options;
	}
	
	public GenerateGrayDegradeMenuButtonListener(JPanel targetPanel) {
		super(targetPanel);
		options.add(new JLabel("Width"));
		JTextField widthParam = new JTextField();
		options.add(widthParam);
		options.add(new JLabel("Height"));
		JTextField heigthParam = new JTextField();
		options.add(heigthParam);
		options.add(ButtonFactory.generateGrayDegradeButton(widthParam,heigthParam));
	}
	
}
