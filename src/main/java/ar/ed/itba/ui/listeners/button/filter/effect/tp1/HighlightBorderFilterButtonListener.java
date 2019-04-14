package ar.ed.itba.ui.listeners.button.filter.effect.tp1;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.weight.HighlightBorderFilter;
import ar.ed.itba.utils.filters.mask.MaskFilter;

import javax.swing.*;

public class HighlightBorderFilterButtonListener extends MaskFilterButtonListener {
	
	public HighlightBorderFilterButtonListener(JTextField maskSideField) {
		super(maskSideField);
	}
	
	@Override
	public MaskFilter getFilter() {
		int maskSide = Integer.parseInt(maskSideField.getText());
		
		return new HighlightBorderFilter(maskSide);
	}
	
	@Override
	public String getName() {
		return "Borders";
	}
}
