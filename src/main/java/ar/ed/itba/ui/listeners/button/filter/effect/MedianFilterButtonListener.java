package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.median.MedianFilter;

import javax.swing.*;

public class MedianFilterButtonListener extends MaskFilterButtonListener {
	
	public MedianFilterButtonListener(JTextField maskSideField) {
		super(maskSideField);
	}
	
	@Override
	public MaskFilter getFilter() {
		int maskSide = Integer.parseInt(maskSideField.getText());
		
		return new MedianFilter(maskSide);
	}
	
	@Override
	public String getName() {
		return "Median";
	}
}
