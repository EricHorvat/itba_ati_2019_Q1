package ar.ed.itba.ui.frames;

import ar.ed.itba.ui.frames.interfaces.EditableImageInterface;
import ar.ed.itba.ui.frames.interfaces.ImageInterface;

import javax.swing.*;
import java.awt.image.BufferedImage;

public abstract class ImageFrame extends ATIFrame {
	
	/*package*/ BufferedImage image;
	/*package*/ final ImageInterface anInterface;
	
	ImageFrame(String text, ImageInterface anInterface){
		super(text);
		this.anInterface = anInterface;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	@Override
	public void build(){
		if (image != null) {
			super.build();
			anInterface.getImageLabel().setIcon(new ImageIcon(image));
		} else {
			/*TODO THROW EXCEPTION*/
		}
	}
	
	@Override
	JPanel getMainPanel() {
		return anInterface.getMainPanel();
	}
}
