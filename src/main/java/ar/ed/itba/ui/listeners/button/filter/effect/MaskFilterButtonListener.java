package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PgmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.utils.filters.MaskFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public abstract class MaskFilterButtonListener implements ActionListener {
	
	protected JTextField maskSideField;
	
	public MaskFilterButtonListener(JTextField maskSideField) {
		this.maskSideField = maskSideField;
	}
	
	public abstract MaskFilter getFilter();
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		ATIImage resultImage = getFilter().applyFilter(EditableImageFrame.instance().getAtiImage());
		
		FrameFactory.fixedImageFrame("TEST", resultImage).buildAndShow();
	}
}
