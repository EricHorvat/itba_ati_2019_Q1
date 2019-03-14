package ar.ed.itba.ui.listeners.button.menu;

import ar.ed.itba.ui.listeners.button.menu.ATIMenuButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FiltersMenuButtonListener extends ATIMenuButtonListener {
	
	private final List<JButton> options = new ArrayList<>();
	
	@Override
	List<JButton> getOptions() {
		return options;
	}
	
	public FiltersMenuButtonListener(JPanel mainPanel, JPanel targetPanel) {
		super(mainPanel, targetPanel);
		options.add(aButton("Filter a"));
		options.add(aButton("Filter b"));
	}
	
	private static JButton aButton( String text){
		JButton button = new JButton(text);
		button.addActionListener(actionEvent -> {
		  JOptionPane.showMessageDialog(null, "Touched "+ text);
		});
		return button;
	}
}
