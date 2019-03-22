package ar.ed.itba.ui.listeners.button.main;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.pixel.Pixel;
import ar.ed.itba.file.pixel.RGBPixel;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FiltersGeneralMenuButtonListener extends ATIMenuButtonListener {
	
	public FiltersGeneralMenuButtonListener() {
		options.add(aButton("Filter a"));
		options.add(aButton("Filter b"));
	}
	
	private static JButton aButton( String text){
		JButton button = new JButton(text);
		button.addActionListener(actionEvent -> {
			
			BufferedImage image = new BufferedImage(250,250,BufferedImage.TYPE_3BYTE_BGR);
			/*TODO REMOVE*/
			Random random = new Random();
			for(int count = 0; count < random.nextInt(60000); count++){
				image.setRGB(random.nextInt(250), random.nextInt(250), random.nextInt(0xFFFFFF));
			}
			EditableImageFrame.instance().setAtiImage(new ATIImage(image, ATIImage.ImageMode.COLOR) {
				BufferedImage img = image;
				
				@Override
				protected BufferedImage open(String filePath) throws IOException {
					return img;
				}
				
				@Override
				public BufferedImage view() {
					return img;
				}
				
				@Override
				public Pixel getPixel(int i, int j) {
					Color c = new Color(img.getRGB(i,j));
					return new RGBPixel((byte)c.getRed(),(byte)c.getBlue(),(byte)c.getGreen());
				}
				
				@Override
				public void setPixel(int i, int j, Pixel pixel) {
					RGBPixel rgbPixel = (RGBPixel)pixel;
					Color c= new Color(rgbPixel.getRed(),rgbPixel.getBlue(),rgbPixel.getGreen());
					img.setRGB(i,j,c.getRGB());
				}
				
				@Override
				public void save(String fileName) throws Exception {
				
				}
				
				@Override
				public String getPixelInfo(int i, int j) {
					return null;
				}
				
				@Override
				public String getRegionInfo(int oi, int oj, int w, int h) {
					return null;
				}
			});
		});
		return button;
	}
}
