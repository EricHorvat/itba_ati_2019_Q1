package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ContrastGeneralMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public ContrastGeneralMenuButtonListener() {
		
		options.add(new JLabel("R1"));
		JTextField r1Field = new JTextField();
		options.add(r1Field);
		options.add(new JLabel("R2"));
		JTextField r2Field = new JTextField();
		options.add(r2Field);
		options.add(MenuOptionButtonFactory.contrastMenuOptionButton(r1Field,r2Field));
	}
}
