package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ContrastGeneralMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public ContrastGeneralMenuButtonListener() {
		
		options.add(new JLabel("R1 X"));
		JTextField r1xField = new JTextField();
		options.add(r1xField);
		options.add(new JLabel("R1 Y"));
		JTextField r1yField = new JTextField();
		options.add(r1yField);
		options.add(new JLabel("R2 X"));
		JTextField r2xField = new JTextField();
		options.add(r2xField);
		options.add(new JLabel("R2 Y"));
		JTextField r2yField = new JTextField();
		options.add(r2yField);
		options.add(MenuOptionButtonFactory.contrastMenuOptionButton(r1xField,r1yField, r2xField, r2yField));
	}
}
