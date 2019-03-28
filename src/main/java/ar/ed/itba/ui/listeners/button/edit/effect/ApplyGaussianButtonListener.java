package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.math.NoiseImageFactory;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.ImageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ar.ed.itba.utils.ImageUtils.toIntArray;

public class ApplyGaussianButtonListener extends ApplyNoiseButtonListener {
	
	private final JTextField sigmaField;
	private final JTextField muField;
	
	public ApplyGaussianButtonListener(JTextField percentageField, JTextField sigmaField, JTextField muField) {
		super(percentageField);
		this.sigmaField = sigmaField;
		this.muField = muField;
	}
	
	@Override
	protected ATIImage applyNoise(ATIImage atiImage, double percentage) {
		double sigma = Double.parseDouble(sigmaField.getText());
		double mu = Double.parseDouble(muField.getText());
		
		double[] noiseImage = NoiseImageFactory.gaussianNoiseImage(atiImage.getWidth(), atiImage.getHeight(), percentage,sigma,mu,0);
		return ImageUtils.sum(atiImage,toIntArray(noiseImage));
	}
}
