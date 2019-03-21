package ar.ed.itba.ui.listeners.button.menu.file;

import ar.ed.itba.ui.components.ButtonFactory;
import ar.ed.itba.ui.listeners.button.menu.ATIMenuButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OpenMenuButtonListener extends ATIMenuButtonListener {
	
	private final List<JComponent> options = new ArrayList<>();
	
	@Override
	public List<JComponent> getOptions() {
		return options;
	}
	
	public OpenMenuButtonListener(JPanel targetPanel) {
		super(targetPanel);
		options.add(new JLabel("File dir"));
		JTextField filePathField = new JTextField();
		options.add(filePathField);
		options.add(ButtonFactory.openFileButton(filePathField));
	}
	
}
