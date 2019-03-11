package ar.ed.itba.ui.listeners;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FileListener extends ATIListener {
	
	private final List<JButton> options = new ArrayList<>();
	
	@Override
	List<JButton> getOptions() {
		return options;
	}
	
	public FileListener(JPanel mainPanel, JPanel targetPanel) {
		super(mainPanel, targetPanel);
		options.add(aButton("a"));
		options.add(aButton("b"));
		options.add(aButton("c"));
	}
	
	private static JButton aButton( String text){
		JButton button = new JButton(text);
		
		return button;
	}
}
