package ar.ed.itba.ui.listeners.button.file.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SaveGeneralMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public SaveGeneralMenuButtonListener() {
		options.add(new JLabel("File name"));
		JTextField filePathField = new JTextField();
		options.add(filePathField);
		options.add(MenuOptionButtonFactory.saveFileMenuOptionButton(filePathField));
	}
	
}
