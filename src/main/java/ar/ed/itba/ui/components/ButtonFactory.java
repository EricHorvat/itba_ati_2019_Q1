package ar.ed.itba.ui.components;

import ar.ed.itba.ui.listeners.button.*;

import javax.swing.*;
public class ButtonFactory {
	
	private ButtonFactory() {
	}
	
	/*FILE*/
	
	public static JButton openFileButton(JTextField filePathField){
		JButton button = new JButton("Open file");
		button.addActionListener(new OpenFileButtonListener(filePathField));
		return button;
	}
	
	public static JButton saveFileButton(JTextField fileNameField){
		JButton button = new JButton("Save file");
		button.addActionListener(new SaveFileButtonListener(fileNameField));
		return button;
	}
	
	/*EDIT*/
	
	public static JButton editPixelButton(JTextField colorField){
		JButton button = new JButton("Set");
		button.addActionListener(new EditPixelButtonListener(colorField));
		return button;
	}
	
	/*GENERATE*/
	
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
	
	public static JButton generateGrayDegradeButton(JTextField widthField, JTextField heigthField){
		JButton button = new JButton("Generate Degrade");
		button.addActionListener(new GenerateGrayDegradeButtonListener(widthField, heigthField));
		return button;
	}
	
	public static JButton generateColorDegradeButton(JTextField color1Field, JTextField color2Field, JTextField widthField, JTextField heigthField){
		JButton button = new JButton("Generate Degrade");
		button.addActionListener(new GenerateColorDegradeButtonListener(color1Field, color2Field, widthField, heigthField));
		return button;
	}
	
	public static JButton generateHSVButton(){
		JButton button = new JButton("Generate HSV");
		button.addActionListener(new GenerateHSVButtonListener());
		return button;
	}
	
}
