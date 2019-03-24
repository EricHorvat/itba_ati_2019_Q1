package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.file.image.PgmImage;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.ui.frames.interfaces.FixedImageInterface;
import ar.ed.itba.utils.filters.HighlightBorderFilter;
import ar.ed.itba.utils.filters.MaskFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class HighlightBorderFilterButtonListener extends MaskFilterButtonListener{
	
	public HighlightBorderFilterButtonListener(JTextField maskSideField) {
		super(maskSideField);
	}
	
	@Override
	public MaskFilter getFilter() {
		int maskSide = Integer.parseInt(maskSideField.getText());
		
		return new HighlightBorderFilter(maskSide);
	}
	
}
