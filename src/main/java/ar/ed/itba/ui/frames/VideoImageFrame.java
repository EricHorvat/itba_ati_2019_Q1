package ar.ed.itba.ui.frames;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.interfaces.EditableImageInterface;
import ar.ed.itba.ui.frames.interfaces.FixedImageInterface;
import ar.ed.itba.utils.Region;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class VideoImageFrame extends ImageFrame {
	private static VideoImageFrame instance;
	
	private VideoImageFrame(){
		super("", new FixedImageInterface());
	}
	
	public static VideoImageFrame instance() {
		if (instance == null) {
			instance = new VideoImageFrame();
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
    return Region.empty();
  }
}
