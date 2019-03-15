package ar.ed.itba.ui.frames;

import ar.ed.itba.ui.frames.interfaces.EditableImageInterface;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class EditableImageFrame extends ImageFrame {
	private static EditableImageFrame instance;
	
	private EditableImageFrame(){
		super("Editable Image", new EditableImageInterface());
	}
	
	public static EditableImageFrame instance() {
		if (instance == null) {
			instance = new EditableImageFrame();
		}
		return instance;
	}
	
	@Override
	JPanel getMainPanel() {
		return anInterface.getMainPanel();
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void showRegionInfo(int oX, int oY, int tX, int tY) {
		((EditableImageInterface)this.anInterface).setInfo("TODO, pos " + oX + " " + oY + " " + tX + " " + tY);
		pack();
	}
	
	public void showPixelInfo(int oX, int oY) {
		int rgb = image.getRGB(oX, oY);
		int redValue = (rgb & 0xFF0000) >> 16;
		int blueValue = (rgb & 0xFF00) >> 8;
		int greenValue = (rgb & 0xFF);
		((EditableImageInterface)this.anInterface).setInfo("TODO pos " + oX + " " + oY + " r" + redValue+ " b" + blueValue+ " g" + greenValue);
		pack();
	}
}
