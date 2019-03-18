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
		((EditableImageInterface)this.anInterface).setInfo("TODO, pos " + oX + " " + oY + " " + tX + " " + tY + " " + atiImage.getRegionInfo(oX, oY, tX, tY));
		pack();
	}
	
	public void showPixelInfo(int oX, int oY) {
		((EditableImageInterface)this.anInterface).setInfo("TODO pos " + oX + " " + oY + " " + atiImage.getPixelInfo(oX, oY));
		pack();
	}
}
