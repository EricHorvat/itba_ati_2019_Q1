package ar.ed.itba.ui.components;

import ar.ed.itba.ui.listeners.button.edit.menu.*;
import ar.ed.itba.ui.listeners.button.file.menu.SaveGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.filter.effect.HighlightBorderFilterButtonListener;
import ar.ed.itba.ui.listeners.button.filter.menu.*;
import ar.ed.itba.ui.listeners.button.generate.menu.*;
import ar.ed.itba.ui.listeners.button.file.menu.OpenGeneralMenuButtonListener;

import javax.swing.*;
import java.awt.event.ActionListener;

public final class MenuButtonFactory {
	
	/* FILE */
	public static JButton openFileMenuButton(){
		return listeningButton("Open File", new OpenGeneralMenuButtonListener());
	}
	
	public static JButton saveFileMenuButton(){
		return listeningButton("Save File", new SaveGeneralMenuButtonListener());
	}
	/* END FILE*/
	
	/* EDIT */
	
	public static JButton setPixelMenuButton(){
		return listeningButton("Edit Pixel", new EditPixelGeneralMenuButtonListener());
	}
	
	public static JButton setAddMenuButton() {
		return listeningButton("Add image", new AddImageGeneralMenuButtonListener());
	}
	
	public static JButton setLessMenuButton() {
		return listeningButton("Less image", new LessImageGeneralMenuButtonListener());
	}
	
	public static JButton setDynamicRangeMenuButton() {
		return listeningButton("Dynamic range",new DynamicRangeGeneralMenuButtonListener());
	}
	
	public static JButton setGammaPowMenuButton() {
		return listeningButton("Gamma Power", new GammaPowerMenuButtonListener());
	}
	
	public static JButton setNegativeMenuButton() {
		return listeningButton("Negative",new NegativeMenuButtonListener());
	}
	
	public static JButton setContrastMenuButton() {
		return listeningButton("Contrast",new ContrastGeneralMenuButtonListener());
	}
	
	public static JButton setThresholdMenuButton() {
		return listeningButton("Threshold", new ThresholdMenuButtonListener());
	}
	
	public static JButton setEqualizationMenuButton() {
		return listeningButton("Equalization", new EqualizationMenuButtonListener());
	}
	
	public static JButton applyGaussianMenuButton() {
		return listeningButton("Apply Gaussian Noise", new ApplyGaussianMenuButtonListener());
	}
	
	public static JButton applyRayleighMenuButton() {
		return listeningButton("Apply Rayleigh Noise", new ApplyRayleighMenuButtonListener());
	}
	
	public static JButton applyExponentialMenuButton() {
		return listeningButton("Apply Exponential Noise", new ApplyExponentialMenuButtonListener());
	}
	
	public static JButton applySaltAndPepperMenuButton() {
		return listeningButton("Apply Salt&Pepper Noise", new ApplySaltAndPepperMenuButtonListener());
	}
	
	/* END EDIT */
	/* GENERATE */
	public static JButton generateCircleMenuButton(){
		return listeningButton("Generate Circle", new GenerateCircleGeneralMenuButtonListener());
	}
	
	public static JButton generateSquareMenuButton(){
		return listeningButton("Generate Square", new GenerateSquareGeneralMenuButtonListener());
	}
	
	public static JButton generateGrayDegradeMenuButton(){
		return listeningButton("Generate Gray Degrade", new GenerateGrayDegradeGeneralMenuButtonListener());
	}
	
	public static JButton generateColorDegradeMenuButton(){
		return listeningButton("Generate Color Degrade", new GenerateColorDegradeGeneralMenuButtonListener());
	}
	
	public static JButton generateHSVMenuButton(){
		return listeningButton("Generate HSV", new GenerateHSVGeneralMenuButtonListener());
	}
	
	public static JButton generateGrayHistogramMenuButton() {
		return listeningButton("Gray Histogram", new GenerateGrayHistogramMenuButtonListener());
	}
	
	public static JButton generateGaussianMenuButton() {
		return listeningButton("Gaussian Noise", new GenerateGaussianMenuButtonListener());
	}
	
	public static JButton generateRayleighMenuButton() {
		return listeningButton("Rayleigh Noise", new GenerateRayleighMenuButtonListener());
	}
	
	public static JButton generateExponentialMenuButton() {
		return listeningButton("Exponential Noise", new GenerateExponentialMenuButtonListener());
	}
	
	/* END GENERATE*/
	/* FILTER */
	
	public static JButton mediaFilterMenuButton() {
		return listeningButton("Media Filter", new MediaFilterMenuButtonListener());
	}
	
	public static JButton medianFilterMenuButton() {
		return listeningButton("Median Filter", new MedianFilterMenuButtonListener());
	}
	
	public static JButton ponderedMedianFilterMenuButton() {
		return listeningButton("Pondered Median Filter", new PonderedMedianFilterMenuButtonListener());
	}
	
	public static JButton gaussianFilterMenuButton() {
		return listeningButton("Gaussian Filter", new GaussianFilterMenuButtonListener());
	}
	
	public static JButton highlightBorderFilterMenuButton() {
		return listeningButton("Highlight Border Filter", new HighlightBorderFilterMenuButtonListener());
	}
	
	/* END FILTER */
	
	/* UTILS */
	
	private static JButton listeningButton(String text, ActionListener listener){
		JButton button = new JButton(text);
		button.addActionListener(listener);
		return button;
		
	}


}
