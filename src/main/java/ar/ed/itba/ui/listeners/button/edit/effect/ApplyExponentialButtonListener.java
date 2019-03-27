package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.math.NoiseImageFactory;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.ImageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyExponentialButtonListener extends ApplyNoiseButtonListener{
	
	private final JTextField lambdaField;
	
	public ApplyExponentialButtonListener(JTextField percentageField,JTextField lambdaField) {
		super(percentageField);
		this.lambdaField = lambdaField;
	}
	
	@Override
	protected ATIImage applyNoise(ATIImage atiImage, double percentage) {
		double lambda = Double.parseDouble(lambdaField.getText());
		
		double[] noiseImage = NoiseImageFactory.exponentialNoiseImage(atiImage.getWidth(), atiImage.getHeight(), percentage,lambda,1);
		return ImageUtils.multiply(atiImage,noiseImage);
	}
	
}
