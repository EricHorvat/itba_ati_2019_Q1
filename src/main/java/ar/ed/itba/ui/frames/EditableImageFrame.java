package ar.ed.itba.ui.frames;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.interfaces.EditableImageInterface;
import ar.ed.itba.utils.Region;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class EditableImageFrame extends ImageFrame {
	private static EditableImageFrame instance;
	
	private EditableImageFrame(){
		super("Editable Image", new EditableImageInterface());
		moves = new ArrayList<>();
		index = -1;
	}
	
	private Region region;
	
	public static EditableImageFrame instance() {
		if (instance == null) {
			instance = new EditableImageFrame();
			/*instance.setListeners()*/
		}
		return instance;
	}
	
	private List<ATIImage> moves;
	private int index;
	
	@Override
	public void setAtiImage(ATIImage atiImage) {
		IntStream.range(index+1,moves.size()).forEach(moves::remove);
		moves.add(atiImage);
		redo();
	}
	
	public void undo(){
		if(index > 0) {
			index--;
			super.setAtiImage(moves.get(index));
			this.makeShow();
		}
	}
	
	public void redo(){
		if(index < moves.size() - 1) {
			index++;
			super.setAtiImage(moves.get(index));
			this.makeShow();
		}
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
	
	public void region(int ox, int oy, int tx, int ty){
		int regionX = ox < tx ? ox : tx;
		int regionY = oy < ty ? oy : ty;
		int regionW = (tx - ox) * (ox < tx ? 1 : -1);
		int regionH = (ty - oy) * (oy < ty ? 1 : -1);
		region = new Region(regionX, regionY, regionW, regionH);
	}
	
	public void noRegion(){
		region = null;
	}
	
	@Override
	public boolean isRegionated() {
		return region != null;
	}
	
	public Region getRegion() {
		return region;
	}
}
