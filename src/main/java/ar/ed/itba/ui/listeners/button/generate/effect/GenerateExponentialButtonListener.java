package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.math.NoiseImageFactory;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.utils.ImageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GenerateExponentialButtonListener extends GenerateNoiseButtonListener {
	
	private final JTextField lambdaField;
	
	public GenerateExponentialButtonListener(JTextField percentageField, JTextField lambdaField) {
		super(percentageField);
		this.lambdaField = lambdaField;
	}
	
	@Override
	protected int[] getImage(int width, int height) {
		double lambda = Double.parseDouble(lambdaField.getText());
		double percentage = Double.parseDouble(percentageField.getText());
		return ImageUtils.toIntArray(NoiseImageFactory.exponentialNoiseImage(width,height, percentage, lambda, 0));
	}
}
