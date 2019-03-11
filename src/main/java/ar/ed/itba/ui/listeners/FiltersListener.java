package ar.ed.itba.ui.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FiltersListener extends ATIListener {
	
	private final List<JButton> options = new ArrayList<>();
	
	@Override
	List<JButton> getOptions() {
		return options;
	}
	
	public FiltersListener(JPanel mainPanel, JPanel targetPanel) {
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
