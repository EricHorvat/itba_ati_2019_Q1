package ar.ed.itba.ui.listeners.button.menu.edit;

import ar.ed.itba.ui.components.ButtonFactory;
import ar.ed.itba.ui.listeners.button.menu.ATIMenuButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EditPixelMenuButtonListener extends ATIMenuButtonListener {
	
	private final List<JComponent> options = new ArrayList<>();
	
	@Override
	public List<JComponent> getOptions() {
		return options;
	}
	
	public EditPixelMenuButtonListener(JPanel targetPanel) {
		super(targetPanel);
		options.add(new JLabel("Color"));
		JTextField colorParam = new JTextField();
		options.add(colorParam);
		options.add(ButtonFactory.editPixelButton(colorParam));
	}
	
}
