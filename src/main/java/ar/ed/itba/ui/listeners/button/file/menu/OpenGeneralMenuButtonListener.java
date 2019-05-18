package ar.ed.itba.ui.listeners.button.file.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OpenGeneralMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public OpenGeneralMenuButtonListener() {
		options.add(new JLabel("File dir"));
		JTextField filePathField = new JTextField();
		options.add(filePathField);
		options.add(MenuOptionButtonFactory.openFileMenuOptionButton(filePathField));
		options.add(MenuOptionButtonFactory.openTESTFileMenuOptionButton());
    options.add(MenuOptionButtonFactory.openLenaFileMenuOptionButton());
    options.add(MenuOptionButtonFactory.openSynteticVideoFileMenuOptionButton());
	}
	
}
