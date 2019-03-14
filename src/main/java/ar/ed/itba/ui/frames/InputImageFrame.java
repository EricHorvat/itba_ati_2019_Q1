package ar.ed.itba.ui.frames;

import ar.ed.itba.ui.frames.interfaces.InputImageInterface;
import ar.ed.itba.ui.frames.interfaces.MainInterface;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class InputImageFrame extends ATIFrame {
	private static InputImageFrame instance;
	private InputImageInterface anInterface = new InputImageInterface();
	
	private BufferedImage image;
	
	private InputImageFrame(){
		super("Editable Image");
	}
	
	public static InputImageFrame instance() {
		if (instance == null) {
			instance = new InputImageFrame();
		}
		return instance;
	}
	
	@Override
	public void build(){
		if (image != null) {
			super.build();
			anInterface.getImageLabel().setIcon(new ImageIcon(image));
		} else {
			/*TODO THROW EXCEPTION*/
		}
	}
	
	@Override
	JPanel getMainPanel() {
		return anInterface.getMainPanel();
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void showRegionInfo(int oX, int oY, int tX, int tY) {
		this.anInterface.setInfo("TODO, pos " + oX + " " + oY + " " + tX + " " + tY);
	}
	
	public void showPixelInfo(int oX, int oY) {
		int rgb = image.getRGB(oX, oY);
		int redValue = (rgb & 0xFF0000) >> 16;
		int blueValue = (rgb & 0xFF00) >> 8;
		int greenValue = (rgb & 0xFF);
		this.anInterface.setInfo("TODO pos " + oX + " " + oY + " r" + redValue+ " b" + blueValue+ " g" + greenValue);
	}
}
