package ar.ed.itba.ui.components;

import ar.ed.itba.ui.listeners.button.edit.effect.*;
import ar.ed.itba.ui.listeners.button.generate.effect.*;
import ar.ed.itba.ui.listeners.button.file.effect.OpenFileButtonListener;
import ar.ed.itba.ui.listeners.button.file.effect.SaveFileButtonListener;

import javax.swing.*;
import java.awt.event.ActionListener;

public final class MenuOptionButtonFactory {
	
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
	
	public static JButton dynamicRangeMenuOptionButton(JTextField coeffField){
		return applyButton(new DynamicRangeButtonListener(coeffField));
	}
	
	public static JButton gammaPowerMenuOptionButton(JTextField gammaField){
		return applyButton(new GammaPowerButtonListener(gammaField));
	}
	
	public static JButton negativeMenuOptionButton(){
		return applyButton(new NegativeButtonListener());
	}
	
	public static JButton contrastMenuOptionButton(JTextField r1Field, JTextField r2Field){
		return applyButton(new ContrastButtonListener(r1Field, r2Field));
	}
	
	public static JButton grayHistogramMenuOptionButton(){
		return applyButton(new GrayHistogramButtonListener());
	}
	
	public static JButton thresholdMenuOptionButton(JTextField tField){
		return applyButton(new ThresholdButtonListener(tField));
	}
	
	public static JButton equalizationMenuOptionButton(){
		return applyButton(new EqualizationButtonListener());
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
