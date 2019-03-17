package ar.ed.itba.ui.components;

import ar.ed.itba.ui.frames.MainFrame;
import ar.ed.itba.ui.frames.interfaces.MainInterface;
import ar.ed.itba.ui.listeners.button.GenerateCircleButtonListener;
import ar.ed.itba.ui.listeners.button.menu.generate.GenerateCircleMenuButtonListener;
import ar.ed.itba.ui.listeners.button.menu.generate.GenerateSquareMenuButtonListener;

import javax.swing.*;

public class MenuButtonFactory {
	
	/* GENERATE */
	public static JButton generateCircleMenuButton(){
		JButton button = new JButton("Generate Circle");
		button.addActionListener(new GenerateCircleMenuButtonListener(MainFrame.instance().getParamPanel()));
		return button;
	}
	
	public static JButton generateSquareMenuButton(){
		JButton button = new JButton("Generate Square");
		button.addActionListener(new GenerateSquareMenuButtonListener(MainFrame.instance().getParamPanel()));
		return button;
	}
	
	/*FILTER*/
}
