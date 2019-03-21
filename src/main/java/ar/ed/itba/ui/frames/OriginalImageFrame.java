package ar.ed.itba.ui.frames;

import ar.ed.itba.ui.frames.interfaces.FixedImageInterface;
import ar.ed.itba.utils.Region;

public class OriginalImageFrame extends ImageFrame {
	
	private static OriginalImageFrame instance;
	
	private OriginalImageFrame(){
		super("Original", new FixedImageInterface());
	}
	
	public static OriginalImageFrame instance() {
		if (instance == null) {
			instance = new OriginalImageFrame();
			/*instance.setListeners()*/
		}
		return instance;
	}
	
	@Override
	public boolean isRegionated() {
		return false;
	}
	
	@Override
	public Region getRegion() {
		return null;
	}
}
