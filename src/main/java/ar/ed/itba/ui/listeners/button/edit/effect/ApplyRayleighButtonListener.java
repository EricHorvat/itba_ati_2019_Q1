package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.math.NoiseImageFactory;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.ImageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyRayleighButtonListener extends ApplyNoiseButtonListener {
	
	private final JTextField phiField;
	
	public ApplyRayleighButtonListener(JTextField percentageField, JTextField phiField) {
		super(percentageField);
		this.phiField = phiField;
	}
	
	@Override
	protected ATIImage applyNoise(ATIImage atiImage, double percentage) {
		double phi = Double.parseDouble(phiField.getText());
		
		double[] noiseImage = NoiseImageFactory.rayleighNoiseImage(atiImage.getWidth(), atiImage.getHeight(), percentage,phi,1);
		return ImageUtils.multiply(atiImage,noiseImage);
	}
}
