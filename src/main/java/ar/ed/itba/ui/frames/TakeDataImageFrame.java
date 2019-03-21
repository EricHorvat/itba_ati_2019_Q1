package ar.ed.itba.ui.frames;

import ar.ed.itba.ui.frames.interfaces.FixedImageInterface;
import ar.ed.itba.utils.Region;

public class TakeDataImageFrame extends ImageFrame {
	private static TakeDataImageFrame instance;
	
	private TakeDataImageFrame(){
		super("Original", new FixedImageInterface());
	}
	
	public static TakeDataImageFrame instance() {
		if (instance == null) {
			instance = new TakeDataImageFrame();
			instance.setListeners();
		}
		return instance;
	}
	
	public void setListeners(){
		throw new RuntimeException("TODO");
	}
	
	@Override
	public boolean isRegionated() {
		return false; /*TODOOOOOOOOOOOOOOOOOOOOOO*/
	}
	
	@Override
	public Region getRegion() {
		return null;
	}
}
