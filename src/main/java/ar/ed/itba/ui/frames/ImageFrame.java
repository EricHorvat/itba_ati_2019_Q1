package ar.ed.itba.ui.frames;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.interfaces.ImageInterface;
import ar.ed.itba.utils.Region;

import javax.swing.*;

public abstract class ImageFrame extends ATIFrame {
	
	/*package*/ ATIImage atiImage;
	/*package*/ final ImageInterface anInterface;
	
	ImageFrame(String text, ImageInterface anInterface){
		super(text);
		this.anInterface = anInterface;
	}
	
	public void setAtiImage(ATIImage atiImage) {
		this.atiImage = atiImage;
	}
	
	public ATIImage getAtiImage() {
		return atiImage;
	}
	
	@Override
	public void build(){
		if (atiImage != null) {
			super.build();
			updateImage();
		} else {
			/*TODO THROW EXCEPTION*/
			throw new RuntimeException("ATI IMAGE IS NULL");
		}
	}
	
	@Override
	protected JPanel getMainPanel() {
		return anInterface.getMainPanel();
	}
	
	public ImageInterface getInterface() {
		return anInterface;
	}
	
	public void updateImage(){
		anInterface.getImageLabel().setIcon(new ImageIcon(isRegionated()?atiImage.regionatedView(getRegion()):atiImage.view()));
	}
	
	@Override
	public void pack() {
		updateImage();
		super.pack();
	}
	
	public abstract boolean isRegionated();
	
	public abstract Region getRegion();
}
