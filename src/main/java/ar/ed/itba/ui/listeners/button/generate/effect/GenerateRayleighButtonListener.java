package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PbmImage;
import ar.ed.itba.file.image.PgmImage;
import ar.ed.itba.file.pixel.GrayPixel;
import ar.ed.itba.file.pixel.Pixel;
import ar.ed.itba.math.NoiseImageFactory;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GenerateRayleighButtonListener implements ActionListener {
	
	private final JTextField xiField;
	
	public GenerateRayleighButtonListener(JTextField xiField) {
		this.xiField = xiField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
			ATIImage image = new ATIImage(NoiseImageFactory.rayleighNoiseImage(100, 100, 1, 1), ATIImage.ImageMode.GRAY) {
				@Override
				protected BufferedImage open(String filePath) throws IOException {
					return bufferedImage;
				}
				
				@Override
				public BufferedImage view() {
					return bufferedImage;
				}
				
				@Override
				public Pixel getPixel(int i, int j) {
					return new GrayPixel((byte)(bufferedImage.getRGB(i,j) & 0xFF));
				}
				
				@Override
				public void setPixel(int i, int j, Pixel pixel) {
				
				}
				
				@Override
				public String getPixelInfo(int i, int j) {
					return ""+(bufferedImage.getRGB(i,j) & 0xFF);
				}
				
				@Override
				public String getRegionInfo(int oi, int oj, int w, int h) {
					return "";
				}
				
				@Override
				public void save(String fileName) throws Exception {
				
				}
			};
			
			EditableImageFrame inputImageFrame = EditableImageFrame.instance();
			inputImageFrame.setAtiImage(image);
			inputImageFrame.buildAndShow();
		
	}
}
