package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PgmImage;
import ar.ed.itba.math.NoiseImageFactory;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GenerateGaussianButtonListener extends GenerateNoiseButtonListener{
	
	private final JTextField sigmaField;
	private final JTextField muField;
	
	public GenerateGaussianButtonListener(JTextField sigmaField, JTextField muField) {
		this.sigmaField = sigmaField;
		this.muField = muField;
	}
	
	@Override
	protected BufferedImage getImage() {
		double sigma = Double.parseDouble(sigmaField.getText());
		double mu = Double.parseDouble(muField.getText());
		return NoiseImageFactory.gaussianNoiseImage(100, 100, 1, sigma,mu);
	}
}
