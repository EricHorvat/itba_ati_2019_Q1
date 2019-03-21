package ar.ed.itba.ui.listeners.button.menu.file;

import ar.ed.itba.ui.components.ButtonFactory;
import ar.ed.itba.ui.listeners.button.menu.ATIMenuButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SaveMenuButtonListener extends ATIMenuButtonListener {
	
	private final List<JComponent> options = new ArrayList<>();
	
	@Override
	public List<JComponent> getOptions() {
		return options;
	}
	
	public SaveMenuButtonListener(JPanel targetPanel) {
		super(targetPanel);
		options.add(new JLabel("File name"));
		JTextField filePathField = new JTextField();
		options.add(filePathField);
		options.add(ButtonFactory.saveFileButton(filePathField));
	}
	
}
