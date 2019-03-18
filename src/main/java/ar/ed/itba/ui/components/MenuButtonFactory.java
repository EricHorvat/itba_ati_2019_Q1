package ar.ed.itba.ui.components;

import ar.ed.itba.ui.frames.MainFrame;
import ar.ed.itba.ui.listeners.button.menu.generate.GenerateCircleMenuButtonListener;
import ar.ed.itba.ui.listeners.button.menu.generate.GenerateSquareMenuButtonListener;
import ar.ed.itba.ui.listeners.button.menu.generate.OpenMenuButtonListener;

import javax.swing.*;

public class MenuButtonFactory {
	
	/* FILE */
	public static JButton openFileMenuButton(){
		JButton button = new JButton("Open File");
		button.addActionListener(new OpenMenuButtonListener(MainFrame.instance().getParamPanel()));
		return button;
	}
	
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
