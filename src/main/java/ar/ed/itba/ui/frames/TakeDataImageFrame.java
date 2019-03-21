package ar.ed.itba.ui.frames;

import ar.ed.itba.ui.frames.interfaces.FixedImageInterface;

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
	
}
