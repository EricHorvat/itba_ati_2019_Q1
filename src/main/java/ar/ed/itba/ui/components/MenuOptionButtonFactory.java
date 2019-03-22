package ar.ed.itba.ui.components;

import ar.ed.itba.ui.listeners.button.edit.effect.*;
import ar.ed.itba.ui.listeners.button.generate.effect.*;
import ar.ed.itba.ui.listeners.button.file.effect.OpenFileButtonListener;
import ar.ed.itba.ui.listeners.button.file.effect.SaveFileButtonListener;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuOptionButtonFactory {
	
	private MenuOptionButtonFactory() {
	}
	
	/*FILE*/
	
	public static JButton openFileMenuOptionButton(JTextField filePathField){
		JButton button = new JButton("Open file");
		button.addActionListener(new OpenFileButtonListener(filePathField));
		return button;
	}
	
	public static JButton saveFileMenuOptionButton(JTextField fileNameField){
		JButton button = new JButton("Save file");
		button.addActionListener(new SaveFileButtonListener(fileNameField));
		return button;
	}
	
	/*EDIT*/
	
	public static JButton editPixelMenuOptionButton(JTextField colorField){
		JButton button = new JButton("Set");
		button.addActionListener(new EditPixelButtonListener(colorField));
		return button;
	}
	
	public static JButton addImageMenuOptionButton(JTextField filePathField){
		return applyButton(new AddImageButtonListener(filePathField));
	}
	
	public static JButton lessImageMenuOptionButton(JTextField filePathField){
		return applyButton(new LessImageButtonListener(filePathField));
	}
	
	public static JButton dynamicRangeMenuOptionButton(JTextField filePathField){
		return applyButton(new DynamicRangeButtonListener(filePathField));
	}
	
	public static JButton gammaPowerMenuOptionButton(JTextField filePathField){
		return applyButton(new GammaPowerButtonListener(filePathField));
	}
	
	public static JButton negativeMenuOptionButton(JTextField filePathField){
		return applyButton(new NegativeButtonListener(filePathField));
	}
	
	public static JButton contrastMenuOptionButton(JTextField filePathField){
		return applyButton(new ContrastButtonListener(filePathField));
	}
	
	public static JButton grayHistogramMenuOptionButton(JTextField filePathField){
		return applyButton(new GrayHistogramButtonListener(filePathField));
	}
	
	public static JButton thresholdMenuOptionButton(JTextField filePathField){
		return applyButton(new ThresholdButtonListener(filePathField));
	}
	
	public static JButton equalizationMenuOptionButton(JTextField filePathField){
		return applyButton(new EqualizationButtonListener(filePathField));
	}
	
	/*GENERATE*/
	
	public static JButton generateCircleMenuOptionButton(JTextField radiusField){
		JButton button = new JButton("Generate Circle");
		button.addActionListener(new GenerateCircleButtonListener(radiusField));
		return button;
	}
	
	public static JButton generateSquareMenuOptionButton(JTextField sideField){
		JButton button = new JButton("Generate Square");
		button.addActionListener(new GenerateSquareButtonListener(sideField));
		return button;
	}
	
	public static JButton generateGrayDegradeMenuOptionButton(JTextField widthField, JTextField heigthField){
		JButton button = new JButton("Generate Degrade");
		button.addActionListener(new GenerateGrayDegradeButtonListener(widthField, heigthField));
		return button;
	}
	
	public static JButton generateColorDegradeMenuOptionButton(JTextField color1Field, JTextField color2Field, JTextField widthField, JTextField heigthField){
		JButton button = new JButton("Generate Degrade");
		button.addActionListener(new GenerateColorDegradeButtonListener(color1Field, color2Field, widthField, heigthField));
		return button;
	}
	
	public static JButton generateHSVMenuOptionButton(){
		JButton button = new JButton("Generate HSV");
		button.addActionListener(new GenerateHSVButtonListener());
		return button;
	}
	
	private static JButton applyButton(ActionListener listener){
		JButton button = new JButton("Apply");
		button.addActionListener(listener);
		return button;
	}
}
