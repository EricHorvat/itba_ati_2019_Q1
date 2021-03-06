package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GammaPowerMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GammaPowerMenuButtonListener() {
		options.add(new JLabel("Gamma"));
		JTextField gammaField = new JTextField();
		options.add(gammaField);
		options.add(MenuOptionButtonFactory.gammaPowerMenuOptionButton(gammaField));
	}
}
