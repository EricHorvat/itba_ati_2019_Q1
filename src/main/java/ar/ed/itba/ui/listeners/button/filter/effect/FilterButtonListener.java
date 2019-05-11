package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.utils.filters.Filter;
import ar.ed.itba.utils.filters.mask.MaskFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class FilterButtonListener implements ActionListener {
	
	public abstract Filter getFilter();
	
	public abstract String getTitle();
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		ATIImage resultImage = getFilter().applyFilter(EditableImageFrame.instance().getAtiImage(),false);
		
		FrameFactory.fixedImageFrame(getTitle(), resultImage).buildAndShow();
	}
}
