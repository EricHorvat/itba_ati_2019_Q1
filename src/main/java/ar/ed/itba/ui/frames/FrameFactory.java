package ar.ed.itba.ui.frames;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.interfaces.FixedImageInterface;
import ar.ed.itba.utils.Region;

public class FrameFactory {
	
	private FrameFactory() {
	}
	
	public static ImageFrame fixedImageFrame(String title, ATIImage image){
		ImageFrame imageFrame = new ImageFrame(title, new FixedImageInterface()) {
			@Override
			public boolean isRegionated() {
				return false;
			}
			
			@Override
			public Region getRegion() {
				return null;
			}
		};
		imageFrame.setAtiImage(image);
		return imageFrame;
	}
	
}
