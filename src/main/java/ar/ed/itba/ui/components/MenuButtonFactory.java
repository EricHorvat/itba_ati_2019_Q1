package ar.ed.itba.ui.components;

import ar.ed.itba.ui.frames.MainFrame;
import ar.ed.itba.ui.listeners.button.edit.menu.*;
import ar.ed.itba.ui.listeners.button.file.menu.SaveGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.generate.menu.*;
import ar.ed.itba.ui.listeners.button.file.menu.OpenGeneralMenuButtonListener;

import javax.swing.*;

public class MenuButtonFactory {
	
	/* FILE */
	public static JButton openFileMenuButton(){
		JButton button = new JButton("Open File");
		button.addActionListener(new OpenGeneralMenuButtonListener());
		return button;
	}
	
	public static JButton saveFileMenuButton(){
		JButton button = new JButton("Save File");
		button.addActionListener(new SaveGeneralMenuButtonListener());
		return button;
	}
	
	/*EDIT*/
	
	public static JButton setPixelMenuButton(){
		JButton button = new JButton("Edit Pixel");
		button.addActionListener(new EditPixelGeneralMenuButtonListener());
		return button;
	}
	
	public static JButton setAddMenuButton() {
		JButton button = new JButton("Add image");
		button.addActionListener(new AddImageGeneralMenuButtonListener());
		return button;
	}
	
	public static JButton setLessMenuButton() {
		JButton button = new JButton("Less image");
		button.addActionListener(new LessImageGeneralMenuButtonListener());
		return button;
	}
	
	public static JButton setDynamicRangeMenuButton() {
		JButton button = new JButton("Dynamic range");
		button.addActionListener(new DynamicRangeGeneralMenuButtonListener());
		return button;
	}
	
	public static JButton setGammaPowMenuButton() {
		JButton button = new JButton("Gamma Power");
		button.addActionListener(new GammaPowerMenuButtonListener());
		return button;
	}
	
	public static JButton setNegativeMenuButton() {
		JButton button = new JButton("Negative");
		button.addActionListener(new NegativeMenuButtonListener());
		return button;
	}
	
	public static JButton setGrayHistogramMenuButton() {
		JButton button = new JButton("Gray Histogram");
		button.addActionListener(new GrayHistogramMenuButtonListener());
		return button;
	}
	
	public static JButton setContrastMenuButton() {
		JButton button = new JButton("Contrast");
		button.addActionListener(new ConstrastGeneralMenuButtonListener());
		return button;
	}
	
	public static JButton setThresholdMenuButton() {
		JButton button = new JButton("Threshold");
		button.addActionListener(new ThresholdMenuButtonListener());
		return button;
	}
	
	public static JButton setEqualizationMenuButton() {
		JButton button = new JButton("Equalization");
		button.addActionListener(new EqualizationMenuButtonListener());
		return button;
	}
	
	/* GENERATE */
	public static JButton generateCircleMenuButton(){
		JButton button = new JButton("Generate Circle");
		button.addActionListener(new GenerateCircleGeneralMenuButtonListener());
		return button;
	}
	
	public static JButton generateSquareMenuButton(){
		JButton button = new JButton("Generate Square");
		button.addActionListener(new GenerateSquareGeneralMenuButtonListener());
		return button;
	}
	
	public static JButton generateGrayDegradeMenuButton(){
		JButton button = new JButton("Generate Gray Degrade");
		button.addActionListener(new GenerateGrayDegradeGeneralMenuButtonListener());
		return button;
	}
	
	public static JButton generateColorDegradeMenuButton(){
		JButton button = new JButton("Generate Color Degrade");
		button.addActionListener(new GenerateColorDegradeGeneralMenuButtonListener());
		return button;
	}
	
	public static JButton generateHSVMenuButton(){
		JButton button = new JButton("Generate HSV");
		button.addActionListener(new GenerateHSVGeneralMenuButtonListener());
		return button;
	}
	
	/*FILTER*/
}
