package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PgmImage;
import ar.ed.itba.math.NoiseImageFactory;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.ImageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GenerateGaussianButtonListener extends GenerateNoiseButtonListener{
	
	private final JTextField sigmaField;
	private final JTextField muField;
	
	public GenerateGaussianButtonListener(JTextField percentageField, JTextField sigmaField, JTextField muField) {
		super(percentageField);
		this.sigmaField = sigmaField;
		this.muField = muField;
	}
	
	@Override
	protected int[] getImage(int width, int height) {
		double percentage = Double.parseDouble(percentageField.getText());
		double sigma = Double.parseDouble(sigmaField.getText());
		double mu = Double.parseDouble(muField.getText());
		return ImageUtils.toIntArray(NoiseImageFactory.gaussianNoiseImage(100, 100, percentage, sigma,mu,0));
	}
}
