package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.laplacian.LoGFilter;
import ar.ed.itba.utils.filters.mask.weight.GaussianFilter;

import javax.swing.*;

public class LoGFilterButtonListener extends MaskFilterButtonListener{
	
	public LoGFilterButtonListener(JTextField maskSideField) {
		super(maskSideField);
	}
	
	@Override
	public MaskFilter getFilter() {
		int maskSide = Integer.parseInt(maskSideField.getText());
		
		return new LoGFilter(maskSide/2);
	}
	
	@Override
	public String getName() {
		return "LoG";
	}
}
