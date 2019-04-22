package ar.ed.itba.ui.listeners.button.filter.effect.tp2.laplacian;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.laplacian.LoGFilter;

import javax.swing.*;

public class LoGFilterButtonListener extends MaskFilterButtonListener {
	
	private final JTextField thresholdField;
	private final JTextField sigmaField;
	
	public LoGFilterButtonListener(JTextField maskSideField, JTextField sigmaField, JTextField thresholdField) {
		super(maskSideField);
		this.sigmaField = sigmaField;
		this.thresholdField = thresholdField;
	}
	
	@Override
	public MaskFilter getFilter() {
		int maskSide = Integer.parseInt(maskSideField.getText());
		int sigma = Integer.parseInt(sigmaField.getText());
		Integer threshold = null;
		
		if (thresholdField != null){
			threshold = Integer.parseInt(thresholdField.getText());
		}
		
		
		
		return new LoGFilter(threshold,sigma, maskSide);
	}
	
	@Override
	public String getName() {
		return "LoG";
	}
}
