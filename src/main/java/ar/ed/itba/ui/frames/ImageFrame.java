package ar.ed.itba.ui.frames;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.interfaces.ImageInterface;

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
			anInterface.getImageLabel().setIcon(new ImageIcon(atiImage.getBufferedImage()));
		} else {
			/*TODO THROW EXCEPTION*/
		}
	}
	
	@Override
	JPanel getMainPanel() {
		return anInterface.getMainPanel();
	}
}
