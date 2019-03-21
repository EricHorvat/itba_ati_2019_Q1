package ar.ed.itba.ui.components;

import ar.ed.itba.ui.frames.MainFrame;
import ar.ed.itba.ui.listeners.button.menu.edit.EditPixelMenuButtonListener;
import ar.ed.itba.ui.listeners.button.menu.file.OpenMenuButtonListener;
import ar.ed.itba.ui.listeners.button.menu.file.SaveMenuButtonListener;
import ar.ed.itba.ui.listeners.button.menu.generate.*;

import javax.swing.*;

public class MenuButtonFactory {
	
	/* FILE */
	public static JButton openFileMenuButton(){
		JButton button = new JButton("Open File");
		button.addActionListener(new OpenMenuButtonListener(MainFrame.instance().getParamPanel()));
		return button;
	}
	
	public static JButton saveFileMenuButton(){
		JButton button = new JButton("Save File");
		button.addActionListener(new SaveMenuButtonListener(MainFrame.instance().getParamPanel()));
		return button;
	}
	
	/*EDIT*/
	
	public static JButton setPixelMenuButton(){
		JButton button = new JButton("Edit Pixel");
		button.addActionListener(new EditPixelMenuButtonListener(MainFrame.instance().getParamPanel()));
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
	
	public static JButton generateGrayDegradeMenuButton(){
		JButton button = new JButton("Generate Gray Degrade");
		button.addActionListener(new GenerateGrayDegradeMenuButtonListener(MainFrame.instance().getParamPanel()));
		return button;
	}
	
	public static JButton generateColorDegradeMenuButton(){
		JButton button = new JButton("Generate Color Degrade");
		button.addActionListener(new GenerateColorDegradeMenuButtonListener(MainFrame.instance().getParamPanel()));
		return button;
	}
	
	public static JButton generateHSVMenuButton(){
		JButton button = new JButton("Generate HSV");
		button.addActionListener(new GenerateHSVMenuButtonListener(MainFrame.instance().getParamPanel()));
		return button;
	}
	
	/*FILTER*/
}
