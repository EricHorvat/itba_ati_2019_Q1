package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.math.NoiseImageFactory;
import ar.ed.itba.ui.components.DialogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GenerateExponentialButtonListener extends GenerateNoiseButtonListener {
	
	private final JTextField lambdaField;
	
	public GenerateExponentialButtonListener(JTextField lambdaField) {
		this.lambdaField = lambdaField;
	}
	
	@Override
	protected BufferedImage getImage() {
		double lambda = Double.parseDouble(lambdaField.getText());
		return NoiseImageFactory.exponentialNoiseImage(100, 100, 1, lambda);
	}
}
