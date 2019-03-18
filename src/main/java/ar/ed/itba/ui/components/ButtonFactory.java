package ar.ed.itba.ui.components;

import ar.ed.itba.ui.listeners.button.GenerateCircleButtonListener;
import ar.ed.itba.ui.listeners.button.GenerateSquareButtonListener;
import ar.ed.itba.ui.listeners.button.OpenFileButtonListener;

import javax.swing.*;
public class ButtonFactory {
	
	private ButtonFactory() {
	}
	
	public static JButton openFileButton(JTextField filePathField){
		JButton button = new JButton("Open file");
		button.addActionListener(new OpenFileButtonListener(filePathField));
		return button;
	}
	
	public static JButton generateCircleButton(JTextField radiusField){
		JButton button = new JButton("Generate Circle");
		button.addActionListener(new GenerateCircleButtonListener(radiusField));
		return button;
	}
	
	public static JButton generateSquareButton(JTextField sideField){
		JButton button = new JButton("Generate Square");
		button.addActionListener(new GenerateSquareButtonListener(sideField));
		return button;
	}
	
}
