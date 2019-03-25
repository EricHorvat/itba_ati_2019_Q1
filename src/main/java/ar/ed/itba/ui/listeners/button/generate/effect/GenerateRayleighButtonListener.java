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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

public class GenerateRayleighButtonListener extends GenerateNoiseButtonListener {
	
	private final JTextField xiField;
	
	public GenerateRayleighButtonListener(JTextField xiField) {
		this.xiField = xiField;
	}
	
	@Override
	protected BufferedImage getImage() {
		/*TODO CHECK FIELD*/
		double xi = Double.parseDouble(xiField.getText());
		return NoiseImageFactory.rayleighNoiseImage(100, 100, 1, xi);

	}
	
}
