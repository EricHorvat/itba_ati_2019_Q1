package ar.ed.itba.ui.listeners.mouse;

import ar.ed.itba.ui.frames.EditableImageFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditableImageMouseAdapter extends MouseAdapter {
	
	private int originX = 0;
	private int originY = 0;
	private int targetX = 0;
	private int targetY = 0;
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		originX = e.getX();
		originY = e.getY();
		EditableImageFrame.instance().showPixelInfo(originX,originY);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		showRegionInfo(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		originX = e.getX();
		originY = e.getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		showRegionInfo(e);
	}
	
	private void showRegionInfo(MouseEvent e) {
		targetX = e.getX();
		targetY = e.getY();
		if (originY != targetY && originX != targetX){
			EditableImageFrame.instance().showRegionInfo(originX,originY,targetX,targetY);
		}
	}
	
}
