package ar.ed.itba.ui.frames;

import ar.ed.itba.ui.frames.interfaces.FixedImageInterface;

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
	
}
