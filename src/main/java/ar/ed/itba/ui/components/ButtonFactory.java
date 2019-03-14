package ar.ed.itba.ui.components;

import ar.ed.itba.ui.listeners.button.OpenFileButtonListener;

import javax.swing.*;
public class ButtonFactory {
	
	private ButtonFactory() {
	}
	
	public static JButton openFileButton(){
		JButton button = new JButton("Open file");
		button.addActionListener(new OpenFileButtonListener());
		return button;
	}
}
