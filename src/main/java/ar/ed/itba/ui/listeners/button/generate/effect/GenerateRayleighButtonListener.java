package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PbmImage;
import ar.ed.itba.file.image.PgmImage;
import ar.ed.itba.file.pixel.GrayPixel;
import ar.ed.itba.file.pixel.Pixel;
import ar.ed.itba.math.NoiseImageFactory;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.histogram.GrayHistogramFrame;
import ar.ed.itba.utils.ImageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

public class GenerateRayleighButtonListener extends GenerateNoiseButtonListener {
	
	private final JTextField phiField;
	
	public GenerateRayleighButtonListener(JTextField percentageField, JTextField phiField) {
		super(percentageField);
		this.phiField = phiField;
	}
	
	@Override
	protected int[] getImage(int width, int height) {
		double phi = Double.parseDouble(phiField.getText());
		double percentage = Double.parseDouble(percentageField.getText());
		return ImageUtils.toIntArray(NoiseImageFactory.rayleighNoiseImage(width, height, percentage, phi,0));
	}
	
}
