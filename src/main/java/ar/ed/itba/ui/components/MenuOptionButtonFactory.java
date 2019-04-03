package ar.ed.itba.ui.components;

import ar.ed.itba.ui.listeners.button.edit.effect.*;
import ar.ed.itba.ui.listeners.button.file.effect.OpenFixedFileButtonListener;
import ar.ed.itba.ui.listeners.button.file.effect.ResetFileButtonListener;
import ar.ed.itba.ui.listeners.button.filter.effect.*;
import ar.ed.itba.ui.listeners.button.generate.effect.*;
import ar.ed.itba.ui.listeners.button.file.effect.OpenFileButtonListener;
import ar.ed.itba.ui.listeners.button.file.effect.SaveFileButtonListener;
import ar.ed.itba.utils.filters.PrewitFilter;

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
	
	public static JButton resetFileMenuOptionButton(){
		JButton button = new JButton("Reset file");
		button.addActionListener(new ResetFileButtonListener());
		return button;
	}
	
	public static JButton openTESTFileMenuOptionButton(){
		JButton button = new JButton("Open Test file");
		button.addActionListener(new OpenFixedFileButtonListener("./res/TEST.PGM"));
		return button;
	}
	
	public static JButton openLenaFileMenuOptionButton(){
		JButton button = new JButton("Open Lena file");
		button.addActionListener(new OpenFixedFileButtonListener("./res/LENA.RAW"));
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
	
	public static JButton dynamicRangeMenuOptionButton(JTextField scalarField){
		return applyButton(new DynamicRangeButtonListener(scalarField));
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
	
	public static JButton applySaltAndPepperMenuOptionButton(JTextField sField){
		return applyButton(new ApplySaltAndPepperButtonListener(sField));
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
	
	public static JButton gaussianFilterMenuOptionButton(JTextField maskSideField){
		return applyButton(new GaussianFilterButtonListener(maskSideField));
	}
	
	public static JButton highlightBorderFilterMenuOptionButton(JTextField maskSideField){
		return applyButton(new HighlightBorderFilterButtonListener(maskSideField));
	}
	
	public static JButton prewitMaxFilterMenuOptionButton(){
		JButton button = applyButton(new PrewitFilterButtonListener(PrewitFilter.PrewitType.MAX));
		button.setText("Max");
		return button;
	}
	
	public static JButton prewitAvgFilterMenuOptionButton(){
		JButton button = applyButton(new PrewitFilterButtonListener(PrewitFilter.PrewitType.AVG));
		button.setText("Average");
		return button;
	}
	
	public static JButton prewitMod2FilterMenuOptionButton(){
		JButton button = applyButton(new PrewitFilterButtonListener(PrewitFilter.PrewitType.MOD));
		button.setText("Mod2");
		return button;
	}
	
	public static JButton prewitMinFilterMenuOptionButton(){
		JButton button = applyButton(new PrewitFilterButtonListener(PrewitFilter.PrewitType.MIN));
		button.setText("Min");
		return button;
	}
	
	public static JButton prewitHorizontalFilterMenuOptionButton(){
		JButton button = applyButton(new PrewitFilterButtonListener(PrewitFilter.PrewitType.HOR));
		button.setText("Horizontal");
		return button;
	}
	
	public static JButton prewitVerticalFilterMenuOptionButton(){
		JButton button = applyButton(new PrewitFilterButtonListener(PrewitFilter.PrewitType.VER));
		button.setText("Vertical");
		return button;
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
