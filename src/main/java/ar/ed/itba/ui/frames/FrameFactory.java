package ar.ed.itba.ui.frames;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.interfaces.FixedImageInterface;
import ar.ed.itba.ui.frames.interfaces.ImageInterface;
import ar.ed.itba.utils.Region;

public class FrameFactory {
	
	private FrameFactory() {
	}
	
	public static ImageFrame fixedImageFrame(String title, ATIImage image){
		FilterImageFrame imageFrame = new FilterImageFrame(title, new FixedImageInterface()) ;
		imageFrame.setAtiImage(image);
		return imageFrame;
	}
	
	private static class FilterImageFrame extends ImageFrame{
		
		public FilterImageFrame(String title, FixedImageInterface anInterface) {
			super(title, anInterface);
			anInterface.getUseButton().setVisible(true);
			anInterface.setFrame(this);
		}
		
		@Override
			public boolean isRegionated() {
			return false;
		}
			
			@Override
			public Region getRegion() {
			return Region.empty();
		}
	}
	
}
