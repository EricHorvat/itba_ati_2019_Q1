package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.weight.MediaFilter;

import javax.swing.*;

public class MediaFilterButtonListener extends MaskFilterButtonListener {
	
	public MediaFilterButtonListener(JTextField maskSideField) {
		super(maskSideField);
	}
	
	@Override
	public MaskFilter getFilter() {
		int maskSide = Integer.parseInt(maskSideField.getText());
		
		return new MediaFilter(maskSide);
	}
	
	@Override
	public String getName() {
		return "Media";
	}
}
