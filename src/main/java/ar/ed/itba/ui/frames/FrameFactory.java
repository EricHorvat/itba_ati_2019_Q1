package ar.ed.itba.ui.frames;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.interfaces.FixedImageInterface;

public class FrameFactory {
	
	private FrameFactory() {
	}
	
	public static ImageFrame fixedImageFrame(String title, ATIImage image){
		ImageFrame imageFrame = new ImageFrame(title, new FixedImageInterface()) {
		};
		imageFrame.setAtiImage(image);
		return imageFrame;
	}
	
}
