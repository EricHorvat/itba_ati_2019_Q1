package ar.ed.itba.ui.frames;

import ar.ed.itba.ui.frames.interfaces.EditableImageInterface;

import javax.swing.*;

public class EditableImageFrame extends ImageFrame {
	private static EditableImageFrame instance;
	
	private EditableImageFrame(){
		super("Editable Image", new EditableImageInterface());
	}
	
	public static EditableImageFrame instance() {
		if (instance == null) {
			instance = new EditableImageFrame();
			/*instance.setListeners()*/
		}
		return instance;
	}
	
	@Override
	JPanel getMainPanel() {
		return anInterface.getMainPanel();
	}
	
	public void showRegionInfo(int oX, int oY, int tX, int tY) {
		int x0,y0,w,h;
		if(oX > tX){
			x0 = tX; w = oX-tX;
		} else {
			x0 = oX; w = tX-oX;
		}
		if(oY > tY){
			y0 = tY; h = oY-tY;
		} else {
			y0 = oY; h = tY-oY;
		}
		((EditableImageInterface)this.anInterface).setInfo("oX: " + oX + " oY: " + oY + " tX: " + tX + " tY: " + tY + " " + atiImage.getRegionInfo(x0, y0, w, h));
		pack();
	}
	
	public void showPixelInfo(int oX, int oY) {
		((EditableImageInterface)this.anInterface).setInfo("X: " + oX + " Y: " + oY + " " + atiImage.getPixelInfo(oX, oY));
		pack();
	}
}
