package ar.ed.itba.ui.components;

import ar.ed.itba.ui.listeners.button.edit.effect.*;
import ar.ed.itba.ui.listeners.button.edit.menu.ApplySaltAndPepperMenuButtonListener;
import ar.ed.itba.ui.listeners.button.file.effect.OpenTESTFileButtonListener;
import ar.ed.itba.ui.listeners.button.filter.effect.*;
import ar.ed.itba.ui.listeners.button.generate.effect.*;
import ar.ed.itba.ui.listeners.button.file.effect.OpenFileButtonListener;
import ar.ed.itba.ui.listeners.button.file.effect.SaveFileButtonListener;

import javax.swing.*;
import java.awt.event.ActionListener;

public final class MenuOptionButtonFactory {
	
	private MenuOptionButtonFactory() {}
	
	/* FILE */
	
	public static JButton openFileMenuOptionButton(JTextField filePathField){
		JButton button = new JButton("Open file");
		button.addActionListener(new OpenFileButtonListener(filePathField));
		return button;
	}
	
	public static JButton openTESTFileMenuOptionButton(JTextField filePathField){
		JButton button = new JButton("Open Test file");
		button.addActionListener(new OpenTESTFileButtonListener(filePathField));
		return button;
	}
	
	public static JButton saveFileMenuOptionButton(JTextField fileNameField){
		JButton button = new JButton("Save file");
		button.addActionListener(new SaveFileButtonListener(fileNameField));
		return button;
	}
	
	/* END FILE*/
	/* EDIT */
	
	public static JButton editPixelMenuOptionButton(JTextField colorField){
		return applyButton(new EditPixelButtonListener(colorField));
	}
	
	public static JButton addImageMenuOptionButton(JTextField filePathField){
		return applyButton(new AddImageButtonListener(filePathField));
	}
	
	public static JButton lessImageMenuOptionButton(JTextField filePathField){
		return applyButton(new LessImageButtonListener(filePathField));
	}
	
	public static JButton dynamicRangeMenuOptionButton(){
		return applyButton(new DynamicRangeButtonListener());
	}
	
	public static JButton gammaPowerMenuOptionButton(JTextField gammaField){
		return applyButton(new GammaPowerButtonListener(gammaField));
	}
	
	public static JButton negativeMenuOptionButton(){
		return applyButton(new NegativeButtonListener());
	}
	
	public static JButton contrastMenuOptionButton(JTextField r1xField, JTextField r1yField, JTextField r2xField,
												   JTextField r2yField){
		return applyButton(new ContrastButtonListener(r1xField, r1yField, r2xField, r2yField));
	}
	
	public static JButton thresholdMenuOptionButton(JTextField tField){
		return applyButton(new ThresholdButtonListener(tField));
	}
	
	public static JButton equalizationMenuOptionButton(){
		return applyButton(new EqualizationButtonListener());
	}
	
	public static JButton applyGaussianMenuOptionButton(JTextField percentageField, JTextField sigmaField, JTextField muField){
		return applyButton(new ApplyGaussianButtonListener(percentageField,sigmaField, muField));
	}
	
	public static JButton applyRayleighMenuOptionButton(JTextField percentageField, JTextField phiField){
		return applyButton(new ApplyRayleighButtonListener(percentageField, phiField));
	}
	
	public static JButton applyExponentialMenuOptionButton(JTextField percentageField, JTextField lambdaField){
		return applyButton(new ApplyExponentialButtonListener(percentageField, lambdaField));
	}
	
	public static JButton applySaltAndPepperMenuOptionButton(JTextField cField, JTextField sField){
		return applyButton(new ApplySaltAndPepperButtonListener(cField, sField));
	}
	
	/* END EDIT */
	/* GENERATE */
	
	public static JButton generateCircleMenuOptionButton(JTextField radiusField){
		return generateButton(new GenerateCircleButtonListener(radiusField));
	}
	
	public static JButton generateSquareMenuOptionButton(JTextField sideField){
		return generateButton(new GenerateSquareButtonListener(sideField));
	}
	
	public static JButton generateGrayDegradeMenuOptionButton(JTextField widthField, JTextField heigthField){
		return generateButton(new GenerateGrayDegradeButtonListener(widthField, heigthField));
	}
	
	public static JButton generateColorDegradeMenuOptionButton(JTextField color1Field, JTextField color2Field, JTextField widthField, JTextField heigthField){
		return generateButton(new GenerateColorDegradeButtonListener(color1Field, color2Field, widthField, heigthField));
	}
	
	public static JButton generateHSVMenuOptionButton(){
		return generateButton(new GenerateHSVButtonListener());
	}
	
	public static JButton generateGrayHistogramMenuOptionButton(){
		return generateButton(new GenerateGrayHistogramButtonListener());
	}
	
	public static JButton generateGaussianMenuOptionButton(JTextField percentageField, JTextField sigmaField, JTextField muField){
		return generateButton(new GenerateGaussianButtonListener(percentageField, sigmaField, muField));
	}
	
	public static JButton generateRayleighMenuOptionButton(JTextField percentageField, JTextField phiField){
		return generateButton(new GenerateRayleighButtonListener(percentageField, phiField));
	}
	
	public static JButton generateExponentialMenuOptionButton(JTextField percentageField, JTextField lambdaField){
		return generateButton(new GenerateExponentialButtonListener(percentageField, lambdaField));
	}
	
	/* END GENERATE */
	/* FILTER */
	
	public static JButton mediaFilterMenuOptionButton(JTextField maskSideField){
		return applyButton(new MediaFilterButtonListener(maskSideField));
	}
	
	public static JButton medianFilterMenuOptionButton(JTextField maskSideField){
		return applyButton(new MedianFilterButtonListener(maskSideField));
	}
	
	public static JButton ponderedMedianFilterMenuOptionButton(){
		return applyButton(new PonderedMedianFilterButtonListener());
	}
	
	public static JButton gaussianFilterMenuOptionButton(JTextField maskSideField, JTextField sigmaField){
		return applyButton(new GaussianFilterButtonListener(maskSideField, sigmaField));
	}
	
	public static JButton highlightBorderFilterMenuOptionButton(JTextField maskSideField){
		return applyButton(new HighlightBorderFilterButtonListener(maskSideField));
	}
	
	/* END FILTER */
	/*Utils*/
	
	private static JButton applyButton(ActionListener listener){
		JButton button = new JButton("Apply");
		button.addActionListener(listener);
		return button;
	}
	
	private static JButton generateButton(ActionListener listener){
		JButton button = new JButton("Generate");
		button.addActionListener(listener);
		return button;
	}
}
