package ar.ed.itba.ui.frames;

import ar.ed.itba.ui.frames.interfaces.FixedImageInterface;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class FrameFactory {
	
	private FrameFactory() {
	}
	
	public static ImageFrame fixedImageFrame(String title, BufferedImage image){
		ImageFrame imageFrame = new ImageFrame(title, new FixedImageInterface()) {
		};
		imageFrame.setImage(image);
		return imageFrame;
	}
	
}
