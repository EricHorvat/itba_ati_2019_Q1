package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.utils.filters.HighlightBorderFilter;
import ar.ed.itba.utils.filters.MaskFilter;
import ar.ed.itba.utils.filters.MediaFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaFilterButtonListener extends MaskFilterButtonListener {
	
	public MediaFilterButtonListener(JTextField maskSideField) {
		super(maskSideField);
	}
	
	@Override
	public MaskFilter getFilter() {
		int maskSide = Integer.parseInt(maskSideField.getText());
		
		return new MediaFilter(maskSide);
	}
}
