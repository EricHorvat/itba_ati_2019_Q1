package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.laplacian.LoGFilter;
import ar.ed.itba.utils.filters.mask.weight.GaussianFilter;

import javax.swing.*;

public class LoGFilterButtonListener extends MaskFilterButtonListener{
	
	private JTextField thresholdField;
	
	public LoGFilterButtonListener(JTextField maskSideField, JTextField thresholdField) {
		super(maskSideField);
		this.thresholdField = thresholdField;
	}
	
	@Override
	public MaskFilter getFilter() {
		int maskSide = Integer.parseInt(maskSideField.getText());
		Integer threshold = null;
		
		if (thresholdField != null){
			threshold = Integer.parseInt(thresholdField.getText());
		}
		
		return new LoGFilter(threshold,maskSide/2);
	}
	
	@Override
	public String getName() {
		return "LoG";
	}
}
